package com.greenmonkeys.verticalprototype

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.media.ExifInterface
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import com.greenmonkeys.verticalprototype.model.Artisan
import com.greenmonkeys.verticalprototype.model.ArtisanContainer
import com.greenmonkeys.verticalprototype.model.persistence.DatabaseContainer
import com.greenmonkeys.verticalprototype.model.persistence.PArtisan
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.image
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.uiThread
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CreateArtisanActivity : AppCompatActivity() {
    var imageField: ImageButton? = null
    private var mCurrentPhotoPath: String? = null
    private var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_artisan)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val firstNameField = findViewById<TextView>(R.id.first_name_field)
        val lastNameField = findViewById<TextView>(R.id.last_name_field)
        val emailField = findViewById<TextView>(R.id.email_field)

        imageField = findViewById(R.id.profile_picture_field)
        imageField?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
            }
            dispatchTakePictureIntent()
        }

        val saveArtisanButton = findViewById<FloatingActionButton>(R.id.save_artisan_button)
        saveArtisanButton.setOnClickListener {
            if (firstNameField.text != null && lastNameField.text != null && emailField.text != null && photoFile != null) {
                val artisan = Artisan(ArtisanContainer.getNextID(), firstNameField.text.toString(), lastNameField.text.toString(), emailField.text.toString(), photoFile!!.absolutePath)

                ArtisanContainer.addArtisan(artisan)

                val resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "PROFILE_PICTURE_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            mCurrentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                photoFile = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(this, "com.greenmonkeys.verticalprototype.fileprovider", it)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, TAKE_PICTURE_INTENT_CODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE_INTENT_CODE && resultCode == Activity.RESULT_OK) {
            val exif = ExifInterface(photoFile?.absolutePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
            val originalImage = BitmapFactory.decodeFile(photoFile?.absolutePath)

            val rotatedBitmap = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(originalImage, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(originalImage, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(originalImage, 270f)
                else -> originalImage
            }

            imageField?.setImageBitmap(rotatedBitmap)
        }
    }

    fun rotateImage(src: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
    }

    // TODO: Figure out what ID to use to get the animation working
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.homeAsUp || item?.itemId == R.id.home) {
            val resultIntent = Intent()
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            return true
        }
        return false
    }
}
