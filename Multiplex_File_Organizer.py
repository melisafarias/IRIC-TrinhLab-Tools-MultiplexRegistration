from operator import contains
import os
from PIL import Image
from tkinter.filedialog import askdirectory
import shutil

Main_folder_path = askdirectory(title = "Select a Folder")
Subfolders = os.listdir(Main_folder_path)
print(Main_folder_path)
for folder in Subfolders :
    print(folder)
    Source_dir = Main_folder_path + "/" + folder
    SubFiles = os.listdir(Source_dir)
    x_coordinate = 0
    y_coordinate = 0
    lookup_x = "x=" + str(x_coordinate)
    lookup_y = "y=" + str(y_coordinate)
    namecheck_X = 0
    namecheck_Y = 0
    NewFolderCheck_X = True
    NewFolderCheck_Y = True
    while(NewFolderCheck_X):
        while(NewFolderCheck_Y):
            for file in SubFiles :
                print(file)
                if lookup_x in file and lookup_y in file:
                    print("in lookup")
                    filenamesplit = file.split('_')
                    print(filenamesplit[len(filenamesplit)-1])
                    ExtensionRemover = filenamesplit[len(filenamesplit)-1].split('.')
                    foldername = filenamesplit[0] + "_" + filenamesplit[1] + "_" + ExtensionRemover[0]
                    print(foldername)
                    Output_dir = Main_folder_path + "/" + folder + "/" + foldername
                    if not os.path.exists(Output_dir):
                        os.makedirs(Output_dir)
                    source_path = os.path.join(Source_dir, file)
                    target_path = os.path.join(Output_dir, file)
                    shutil.move(source_path, target_path)
                    print(f"Moved {file} to {Output_dir}")
                else:
                    namecheck_Y = namecheck_Y + 1
            print(namecheck_Y)
            if namecheck_Y >= len(SubFiles):
                print("hi")
                NewFolderCheck_Y = False
                namecheck_X = namecheck_X + 1
                namecheck_Y = 0
            else:
                y_coordinate = y_coordinate + 4928
                lookup_y = "y=" + str(y_coordinate)
                print("Y=" + lookup_y)
                namecheck_X = 0
                namecheck_Y = 0
        print("in X")
        if namecheck_X < 2:
            print("in check X")
            x_coordinate = x_coordinate + 4928
            y_coordinate = 0
            lookup_x = "x=" + str(x_coordinate)
            lookup_y = "y=" + str(y_coordinate)
            NewFolderCheck_Y = True
        else:
            NewFolderCheck_X = False

#yourpath = folder_path

'''def organize_files(source_dir, target_dir):
    if not os.path.exists(target_dir):
        os.makedirs(target_dir)

    for filename in os.listdir(source_dir):
        if os.path.isfile(os.path.join(source_dir, filename)):
            file_extension = filename.split(".")[-1]
            target_subdir = os.path.join(target_dir, file_extension)

            if not os.path.exists(target_subdir):
                os.makedirs(target_subdir)

            source_path = os.path.join(source_dir, filename)
            target_path = os.path.join(target_subdir, filename)

            shutil.move(source_path, target_path)
            print(f"Moved {filename} to {target_subdir}")'''