package com.example.kidsdrawingapp

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var drawingView : DrawingView? = null
    private var seekBarProgress : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        var brushBtn : ImageButton = findViewById(R.id.ib_brush)

        brushBtn.setOnClickListener {
            showBrushSizeChooserDialog()
        }


    }


    private fun showBrushSizeChooserDialog(){

        val brushDialog = Dialog(this)
        brushDialog.setTitle("Brush Size")
        brushDialog.setContentView(R.layout.seekbar_size_pikker)

        var seekBarPiker: SeekBar = brushDialog.findViewById(R.id.seekbar)
        var brushSizeText : TextView? = brushDialog.findViewById(R.id.text_view)
        if(seekBarProgress != null){
            seekBarPiker.progress = seekBarProgress!!
            brushSizeText?.text = seekBarProgress!!.toString()
        }

        seekBarPiker.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                var brushSize = progress.toFloat()
                drawingView?.setSizeForBrush(brushSize)
                seekBarProgress = progress
                brushSizeText?.text = progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped

            }

        }

        )





        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.15).toInt()

        brushDialog.window?.setLayout(width, height)


//        var smallBtn : ImageButton = brushDialog.findViewById(R.id.id_small)
//        smallBtn.setOnClickListener{
//            drawingView?.setSizeForBrush(10.toFloat())
//            brushDialog.dismiss()
//        }
//
//        var mediumBtn : ImageButton = brushDialog.findViewById(R.id.id_medium)
//        mediumBtn.setOnClickListener{
//            drawingView?.setSizeForBrush(20.toFloat())
//            brushDialog.dismiss()
//        }
//
//        var largeBtn : ImageButton = brushDialog.findViewById(R.id.id_large)
//        largeBtn.setOnClickListener{
//            drawingView?.setSizeForBrush(30.toFloat())
//            brushDialog.dismiss()
//        }


        brushDialog.show()

    }
}