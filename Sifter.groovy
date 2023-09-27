import qupath.lib.objects.PathObject
import java.io.File
import qupath.lib.roi.RectangleROI
import qupath.lib.gui.QuPathGUI
import java.awt.image.BufferedImage
import qupath.lib.images.servers.ImageServerProvider
import qupath.lib.gui.commands.ProjectCommands
import groovy.io.FileType
import qupath.lib.images.servers.ImageServer
import qupath.lib.images.servers.ImageServerBuilder
import qupath.lib.regions.RegionRequest
//import java.nio.file.StandardCopyOption.*
import java.nio.*
import java.nio.file.*

//import java.nio.file.attribute.BasicFileAttributes;

// Define the paths to your image files
def directoryPath ='/Users/p0832974/Desktop/mx_01' // List of image file paths
def directoryPath1 = Paths.get(directoryPath)

 def files = []

def Tile_allRounds = []
def directory = new File(directoryPath)

if (directory.exists() && directory.isDirectory()) {
    
    // Get all files in the directory
     files = directory.listFiles()
     
     }
    
    
char seg = 65;  
  
int tilecount = 1;

checkTileCount = 0;
checkSegCount = 0;
//checkFileCount = 0;

int index = 0;

CheckSeg = true;

while(CheckSeg) {
    
    // New Directory Path
    def new_directory = new File("C:" + "\\" + directoryPath + "\\" + seg + tilecount);
    new_directory.mkdir();
    destinationPath = (directoryPath + "\\" + seg + tilecount)
    destinationPath1 = Paths.get(destinationPath)
   //CREATE A NEW PROJECT
    //def project = Projects.createProject(new_directory, BufferedImage.class);
    for (i=0; i < files.length; i++) {
        if (files[i].toString().contains("_"+ seg + "_" + tilecount)) {
            def SourcePath = directoryPath1.resolve(files[i].getName())
            def DestPath = destinationPath1.resolve(files[i].getName())
            Files.move(SourcePath, DestPath)
             
        }
        else {
            checkTileCount++;
        }
        
        //if (!files[i].toString().contains("_"+ seg + "_")) {
          //  checkSegCount++;
        //}
        
    }
    
  

// Function to perform Rigid x2 + Affine registration for the provided image server
       // def performRigidAndAffineRegistration(referenceImageData, imageData) {
       // def aligner = referenceImageData.getStitchingAligner()
       // aligner.alignWith(imageData,RegionRequest.createRigid())
       // aligner.alignWith(imageData, RegionRequest.createRigid())
       // aligner.alignAll(imageData, RegionRequest.createAffine())
       //      }

    
     // Create an ImageServer for the reference image (the first image in the list)
        // def referenceImageFile = imageFiles[0]
        // def referenceImageServer = ImageServerBuilder.createBuilder().imageFile(referenceImageFile).build()
        // def referenceImageData = qupath.openImage(referenceImageServer)


    // Extract the DAPI channel (first channel)
          //  def dapiChannelIndex = 0 // Assuming DAPI channel is the first one
          //  def dapiChannel = referenceImageData.getChannel(dapiChannelIndex)


            // Create an ImageServer containing only the DAPI channel
            //def dapiImageServer = ImageServerBuilder.createBuilder().channels(dapiChannel).build()
            // def referenceDapiImageData = qupath.openImage(dapiImageServer)
     

       // Perform affine registration based on the DAPI channel for the reference image    -- necessary?
      //  performRigidAndAffineRegistration(referenceDapiImageData, referenceImageData)     




// Loop through each image file (excluding the reference image) and align them based on the reference image
      //  for (int i = 1; i < imageFiles.size(); i++) {
        //    def imageFile = imageFiles[i]

            // Create an ImageServer containing only the current image file
          //  def imageServer = ImageServerBuilder.createBuilder().imageFile(imageFile).build()
         // def imageData = qupath.openImage(imageServer)

            // Extract the DAPI channel (first channel) from the current image
          //  def dapiChannel = imageData.getChannel(dapiChannelIndex)

            // Create an ImageServer containing only the DAPI channel for the current image
           // def dapiImageServer = ImageServerBuilder.createBuilder().channels(dapiChannel).build()
           // def dapiImageData = qupath.openImage(dapiImageServer)

            // Perform affine registration based on the DAPI channel using the reference image as the reference
            //performAffineRegistration(referenceDapiImageData, dapiImageData)

            // Apply the same transformation to all other channels for the current image
            //def aligner = imageData.getStitchingAligner()
            //aligner.applyToOtherChannels(dapiImageData)

           
        //}

   
  //  }
//}


   // Function to remove the DAPI channel from the ImageData object                 //loop for each round 
      //  def removeDAPIChannel(imageData) {
      // def channelDisplay = imageData.getServer().getChannelDisplay()
      // def dapiChannelIndex = 0 // Assuming DAPI channel is the first one
      // channelDisplay.removeChannel(dapiChannelIndex)
      // } 
      
      
    // Remove the DAPI channel from each ImageData object
       // def imageDataList = qupath.getImageData()
       // imageDataList.each { imageData ->
         //   removeDAPIChannel(imageData)
       // }

    

// Function to export the aligned tile image as OME-TIFF                    
    //def exportAlignedTileAsOMETIFF(imageData, outputPath) {
        //  def exporter = imageData.getServer().getExporter()
 
        //Export the entire aligned image as OME-TIFF
        //def alignedImage = exporter.exportImage()

        //Save the aligned image as OME-TIFF to the output path
        //ImageIOHelper.writeOMETIFF(alignedImage, outputPath)
    //}


//fileName= seg + tilecount;
//writeImage(getCurrentServer(), fileName)

 
    // Export the aligned image as OME-TIFF
         //  exportAlignedTileAsOMETIFF(imageData, "new_directory${imageFile.name}_aligned.ome.tif")

   
    
    if (checkTileCount >= files.length - 1 ) {
        seg= (char) (seg+1);
        tilecount = 1;
    
    }
    else {
        
        tilecount++;
    
    }
    checkTileCount = 0;
    
    for (i=0; i < files.length; i++) {
        if (!files[i].toString().contains("_"+ seg + "_" + tilecount)) {
            checkTileCount++;
        }
    }
    
    if (checkTileCount >= files.length - 1 ) {
        seg= (char) (seg+1);
        tilecount = 1;
    
    }
    
    checkTileCount = 0;
    
    for (i=0; i < files.length; i++) {
        if (!files[i].toString().contains("_"+ seg + "_")) {
            checkSegCount++;
        }
    }
    
    if(checkSegCount >= files.length - 1) {
        CheckSeg = false;
    }
    else {
        checkSegCount = 0;
    }
    
}




/* Zean: je pense qu'on va devoir faire un separate code using the tile folders pour stack the 12 rounds of each. open project, close, open next , close 
  otherwise if we use files[i] in this loop, on aura tous les tiles melanges, ca va etre complique de stack the good ones 
*/






