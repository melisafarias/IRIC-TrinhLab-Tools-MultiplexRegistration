//Script to merge specified channels of an image onto a new image for further analysis

setBatchMode(true);
//Specify proper path
//inputDir = getDirectory("Open Multiplex File Directory");
//print(inputDir);
inputDir = "C:/Users/p0832974/OneDrive/IRIC Trinh Lab/Multiplex Qupath Project/tiles/mx_01_r01/";
//outputDir = getDirectory("D:/OneDrive - Gmail/OneDrive/IRIC Trinh Lab/Multiplex Qupath Project//"); 

fd = getFileList(inputDir);

n = lengthOf(fd);

//Building array to read folder containing all tiles of all images  
numberOfChannels =0;	
SetofChannel = newArray(12);
m = 0;

segment = 65; // ASCII code of 65 is 'A'
CheckTile = true;
tilecount = 1;
countT = 0;
CheckImage = true; 
i = 1;
countOT = 0;
//for (i = 1 ; i< 73; i++) { // change to proper number of multiplex images+1
while (CheckImage){
	while(CheckTile){
		for (j = 1; j < 6 ; j++){      // change loop to proper number of rounds+1
			for (f = 0 ; f < n ; f++){   // total number of tiles 
				if (matches(fd[f], "mx_0"+ i +"_r0"+ j +"_" + fromCharCode(segment) +"_" + tilecount + ".ome.tif")){
					print("mx_0"+ i +"_r0"+ j +"_" + fromCharCode(segment) +"_" + tilecount + ".ome.tif");
					//open(inputDir+fd[f]);
					//SetofChannel[m] = nChannels();
					//m = m + 1;
					countOT++;
					countT++;
					print("countOT=" + countOT);
				}		
			} 
		}
		//stack
		//sift
		//remove DAPI
		//save
		//close
		if(countT >= n){
			CheckTile = false;
			segment = 'A';
			tilecount = 1;
			countT = 0;
		}
		tilecount++;
		if (tilecount > 5){ // change number of rounds accordingly
			segment = (segment + 1);
			tilecount = 1;
			i = 1;
		}
	}
	
	CheckTile = true;
	if(countOT >= n){
		CheckImage = false;
	}
	else{
		i++;
	}
}

























/*
for(i=0; i< inputFiles.length; i++) {
    print("Processing: " + inputDir + inputFiles[i]);
    open(inputDir+inputFiles[i]);
	selectWindow(inputFiles[i]);
    run("Stack to Images");
	run("Merge Channels...", "c1=[#2 DAPI] c2=[#2 AF488] c3=[#2 AF594] c4=[#2 AF647] create");
    saveAs("Tiff", outputDir+inputFiles[i]+".tif" );
	print("Done for " + inputDir + inputFiles[i]);
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	close();
	//may need to add more "close()" to close all oppened windows before new channel merging occurence
}*/


