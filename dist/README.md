Welcome! This is a tool that can read the binary data of Minecraft maps and export it to a .png file!

REQUIREMENTS:

    Minecraft
    Java (if you have Minecraft Java edition you're good)
    NBTExplorer (this is a tool to edit world data for minecraft worlds)

How to use it: You need to use the NBTExplorer tool to extract the data needed from your minecraft world. Launch up the tool and go to your world folder. Open it and go to the data folder and open it. Now you should see a bunch of files called map_X.dat, where X is a number.

Open the one you wish to turn into a png, open the data opackage that shows up. In that you can see a lot of things all the way at the bottom should be something called colors that has 16384 bytes. Double click the file and click the export button.

You can enter whatever name you wish but the extension HAS to be .mmd or else the png converter won't be able to read the file. Click save and now you can use the converter.

Run the app and pick a minecraft verrsion (enter the name). This is neccesary because the color tables change from version to version. There will be a version of the 1.13 color table already preinstalled. You can also add your own color table, check the minecraft wiki on 'Map item format' for more information on color tables.

Now you can load a map. click the load map button and select the file you saved before. It will load the file and show a preview of the map. If you are happy with the map you got you can export it via the Export button.

If your world already has quite a few maps there might be a lot of map_X.dat files to go through. Sadly there's no way to shorten this, maps get created by whatever magic mojang and microsoft have decided to use. Be patient and sort through your maps.

I also suggest you make a list of which map_X.dat file relates to which map in your world. Example: 1-> empty 2-> empty 3-> base 4-> north of base 5-> empty

Good luck and I hope this will be useful.
