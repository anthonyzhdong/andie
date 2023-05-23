## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


# ANDIE - A Non Destructive Image Editor

ANDIE is a program that allows a user to alter a given image. Examples are changing the brightness or increasing the size. These are offered as options in a menu bar at the top of the window. The program itself appears as a window, with a frame in the middle of the window that displays the image. It is non-destructive as it can preserve the original image. 

---
## Requirements

ANDIE requires a JDK of version 17 or later to function as intended. 
Other than this, there are no special requirements.

---
## Table of Contents (Optional)

If your README is long, add a table of contents to make it easy for users to find what they need.

- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [License](#license)
- [Features](#features)
- [Milestones](#milestones)
- [Versions](#versions)

---
## Installation

- Install OpenJDK (preferably Version 17 or later) from [https://jdk.java.net/archive/]

- Boom, ANDIE should run!

---
## Usage


- Begin by running the ANDIE executable

- Navigate to the File option in the menu at the top of the window
![alt text](assets/images/FileCapture.PNG)

- Click on it, and select Open

- Find a suitable image file to use in ANDIE and select it
![alt text](assets/images/FindImage.PNG)

- Try various options and their affects on the image!
![alt text](assets/images/GreyScaleExample.PNG)

- If you want to revert a change made, navigate to the Undo option and click it

- Select the Undo option to revert a change
![alt text](assets/images/UndoingGreyScale.PNG)

- If you would like the text to be in a different language, click settings and select a given language. (Version 1.1 has 4 available, English, Maori, French, Spanish)
![alt text](assets/images/LanguageChange.PNG)

- And finally to save your changed image, select File, then one of the options save, save as, or export. Save overwrites the original with the altered version, save as saves a new copy to a location
of your choosing, and export allows you to explicity specify file type on save.
![alt text](assets/images/Export%20Capture.PNG)

---
## Credits

This was collaborative effort of Carinn, Anthony, Lachlan and Liam. 
GitLab accounts are gasca681, donan928, grala465 and kerli421.

The base project was supplied by the Computer Science Department of Otago University
In particular David Eyers of said department provided a lot of the base code. 



---
## License

Copyright (c) [2003] [Liam Aran Kerr]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## Features

- Open, Save, Export images of type PNG, JPG and GIF

- Undo and Redo changes made to an image in the editor

- Zoom in and out, and reset the zoom scale

- Apply a soft blur, sharpen, Gaussian and Mean filters to the image

- Make the image greyscale and alter its brightness and contrast

- Increase/decrease an images size, rotate the image by 90 degrees (left & right) and 180 degrees, and flip the image vertically/horizontally

- And change the language of the editor to either English (default), Maori, French or Spanish

- Select a rectangular region of the image

- Crop the image based on a selected region

- Draw shapes based on a user selected region 

- Apply emboss filters and Sobel filters

- Create macros that record a sequence of events and can apply that sequence of actions at the users liking

- Keyboard shortcut
 
--- 

## Milestones

### Milestone 1

**Who did what**
Carinn completed Sharpen, Soft Blur, Gaussian Blur, and Median filter.

Anthony completed Change Size, Rotate Image and Image Flip. 

Lachlan completed Multilingual Support, Image Export and Error Handling. 

Liam completed Brightness and Contrast, writing the README documentation and some JUnit testing.

**Code Testing** 

Code was mainly tested through brute force methods rather than JUnit testing, a fact that we will change for the next submission. After implementing some JUnit testing very late into our developement of ANDIE for the first milestone, we discovered how useful having unit tests to check previous bits of code could be. We will be implementing them for the next part of ANDIE. Lachlan in particular did the bulk of the brute force testing, as he was developing the error handling. 

As for brute force testing, a lot of it was done with print statements, and passing in images to ANDIE to check if our methods were working correctly, rather than using JUnit testing. This was done on an individual basis, as each team member worked on their part of the project, they would test and ensure their piece of software was working correctly. Lachlan went through and tried to break ANDIE repeatedly, and we found several more errors from that. We had a set of images we used for testing ANDIE, a couple .jpg files, a couple .png files with some transparent background to ensure our software could handle ARGB input, and a .gif file to test how ANDIE handles it. 

The reason we did far more brute force testing was because of the fact that ANDIE is a pretty self diagnosing program, as in when something is wrong with an filter alogrithm for instance, it was pretty obvious there is an error there, and often one could guess what part of the filter was going wrong. So often the easiest method of testing was just running ANDIE and seeing what happened. 

However, in the next part of developing ANDIE, more unit testing will be used, as it is extremely useful and fast once completed to ensure an algorithm is still functioning as expected. Currently, there are only 2 proper testing files, being for BrightnessContrast and ImagePanel. 

---

### Milestone 2

**Who did what**
Carinn completed the Emboss filters, the 2 Sobel Filters, extended the existing filters and created the additional Blotch Blur.

Anthony completed the Eyedropper tool, the 2 Color menus, and Shape Drawing (joint-effort with Liam)

Lachlan completed Macros, Toolbar, and Keyboard Shortcuts.

Liam completed Mouse-Based Image Selection, Shape Drawing (joint-effort with Anthony) and Image Crop 

**Code Testing** 

Again, we ended up mainly brute force testing ANDIE rather than JUnit testing. This was because we found that for the next section of tasks, only the filters were being altered in any major way. And because we hadn't written tests for them prior, there wasn't much point writing tests now. JUnit tests seem to be more useful for long-running projects, where many parts of the software are being altered all the time, and constant testing is much more important. However with ANDIE, we are developing new features more than extending or changing old ones, and JUnit tests aren't that helpful for testing when you're developing a feature, only when you're extending a existing one. Because of this, rather than use time devloping unit tests retro-actively, we found it more useful to just brute force test changes (like extending the filters) or new features. 

This brute force testing was done mainly through print statements and testing output in ANDIE itself. This was done largely individually as each team member worked on their assigned portions or work to complete. Unlike last time, this was more of shared effort rather than one singular person doing most of the bug testing. We used the same test files as last time, to ensure that old features were still working correctly and test that new feature behaved correctly on known images.

Again, the reason we did far more brute force testing was because of the fact that ANDIE is a pretty self diagnosing program, as in when something is wrong with an filter alogrithm for instance, it was pretty obvious there is an error there, and often one could guess what part of the filter was going wrong. So rather than unit tests the easier method to test was to chuck a test image in ANDIE and see what happens.

If we had the chance to re-do this project, I think from the start we would implement unit tests as each feature is developed, so that from the beginning we can constantly test ANDIE to ensure consistency and functionality of a feature once its added. This would also mean that going into Milestone 2 from Milestone 1, we would have unit tests that we know work already, and if they start failing once we change things in Milestone 2, we know exactly where.

---

## Versions

1.0 : Base form of ANDIE supplied by Department of Computer Science.

2.0 : Added Sharpen, Gaussian blur, mean filter, soft blur, brightness and contrast altering, increase size, rotate by 90 degrees, flip, and multilingual support

3.0 : Extends filters to handle edges cases. Added the 8 emboss filters, the 2 Sobel filters, a toolbar with options for commonly used options, keyboard shortcuts, macros for applying a preset set of operations in a sequence, rectangular selection, image crop to said rectangular selection, drawing operations (draw rectangle, oval, line), and additionally added a custom filter blotch blur, a paintbrush tool, an eyedropper tool, and menues for selecting hollow or fill shapes, shape color and line thickness for hollow shapes and paintbrush.
