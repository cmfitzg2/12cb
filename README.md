# Full tutorial
https://www.youtube.com/watch?v=f_DaKsksumc

# Overview
A stream-friendly tool for tracking a twelve character battle in Smash 64

Note: at a minimum, this tool will require the Java Runtime Environment (JRE) to run. To see if you have it, launch a command prompt / terminal window and type `java -version` and see if the command is recognized. If not, follow this guide for installing Java and get out of the stone age.
https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB

The JDK includes the JRE, and will also allow you to modify / compile this program locally. If that isn't your intent, the JRE by itself is fine.

# How to operation
#### Easy to operation, simple: 

After installing the JRE, simply download the executable JAR included in this project, save it anywhere, and run it. If your browser gives you trouble, try downloading the .zip version, which just contains the JAR inside it.

#### Difficult to operation, complex: 

Install the JDK, download the source code, and manually compile it after reading through all the source code to make sure I'm not giving you a keylogger or something.

After running, a small config window will pop-up that will prompt you to enter the number of stocks to use for the 12cb. After inputting and hitting start, 4 windows will launch. The tall windows can be used as either the primary capture windows for the stream for Player 1 / Player 2, or as the config managers for the single-character windows (and thus not captured as stream elements themselves). If you're doing the former, you can safely close / ignore the single-character windows and the program will run normally. If you're doing the latter, the tall windows will be used to switch the active character for each player and as a way to update remaining stocks.

Clicking a character in the tall window will do one of two things:
1. If the character is not already selected, it will set the active character to the clicked one
2. If it is already selected, it will decrement the stock count for that character. When the stock count hits 0, the character's profile will be replaced with a grayed out picture. If clicked again while active and at 0 stocks, the character's stock count will reset (to help with user error).

# Details
The smaller, single-character windows are intended as a more succinct summary of each player's remaining stocks / characters, designed to be less demanding on stream real estate. Remaining stocks for each player will be rendered as icons next to the active character's profile, and will update as changes are made in the config window. 

Both window types are resizable. This allows the user to more easily fit them into their overlay, or to minimize the size of the config window while still allowing it to be used for control. The single-character windows will wrap the stock icons to fit the window size.

Lastly, the background color for both windows (most importantly the single-character window) was carefully selected as an easy color to target with a Color Key filter that doesn't have any overlap with the character profiles or icons. Here are some OBS Color Key values that have worked well for me: 

- Color RGB: 0, 191, 255 
- Similarity: 234
- Smoothness: 185

# Modifications
If you want to change the source images, just use a typical archive tool like 7zip or WinRAR to open the directory. By default, the images in the JAR are just bundled in uncompressed, so you can simply replace the images in the archive with your modified image, and it will work as long as you honor the dimensions in the images. 

Contact me on facebook (Collin Fitzgerald) / twitter (@narwhal_reborn) / discord (lord narwhal #2879) if you have any issues.

# Screenshots

![App screenshot 1](https://github.com/cmfitzg2/12cb/blob/master/res/promo-art/UI-0.png?raw=true)<br/>
![App screenshot 2](https://github.com/cmfitzg2/12cb/blob/master/res/promo-art/UI-1.png?raw=true)<br/>
![App screenshot 3](https://github.com/cmfitzg2/12cb/blob/master/res/promo-art/UI-2.png?raw=true)<br/>
