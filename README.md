## NOTICE

This repository contains the public FTC SDK for the CENTERSTAGE (2023-2024) competition season.

## Welcome!
This GitHub repository contains the source code that is used to build an Android app to control a *FIRST* Tech Challenge competition robot.  To use this SDK, download/clone the entire project to your local computer.

## Requirements
To use this Android Studio project, you will need Android Studio 2021.2 (codename Chipmunk) or later.

To program your robot in Blocks or OnBot Java, you do not need Android Studio.

## Getting Started
If you are new to robotics or new to the *FIRST* Tech Challenge, then you should consider reviewing the [FTC Blocks Tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html) to get familiar with how to use the control system:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Blocks Online Tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html)

Even if you are an advanced Java programmer, it is helpful to start with the [FTC Blocks tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html), and then migrate to the [OnBot Java Tool](https://ftc-docs.firstinspires.org/programming_resources/onbot_java/OnBot-Java-Tutorial.html) or to [Android Studio](https://ftc-docs.firstinspires.org/programming_resources/android_studio_java/Android-Studio-Tutorial.html) afterwards.

## Downloading the Project
If you are an Android Studio programmer, there are several ways to download this repo.  Note that if you use the Blocks or OnBot Java Tool to program your robot, then you do not need to download this repository.

* If you are a git user, you can clone the most current version of the repository:

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;git clone https://github.com/FIRST-Tech-Challenge/FtcRobotController.git</p>

* Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  Downloading the project as a .ZIP file will keep the size of the download manageable.

* You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the [Releases](https://github.com/FIRST-Tech-Challenge/FtcRobotController/releases) page for this repository.

* The Releases page also contains prebuilt APKs.

Once you have downloaded and uncompressed (if needed) your folder, you can use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").

## Getting Help
### User Documentation and Tutorials
*FIRST* maintains online documentation with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access this documentation using the following link:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FIRST Tech Challenge Documentation](https://ftc-docs.firstinspires.org/index.html)

Note that the online documentation is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Click on the following link to view the FTC SDK Javadoc documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Javadoc Documentation](https://javadoc.io/doc/org.firstinspires.ftc)

### Online User Forum
For technical questions regarding the Control System or the FTC SDK, please visit the FIRST Tech Challenge Community site:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FIRST Tech Challenge Community](https://ftc-community.firstinspires.org/)

### Sample OpModes
This project contains a large selection of Sample OpModes (robot code examples) which can be cut and pasted into your /teamcode folder to be used as-is, or modified to suit your team's needs.

Samples Folder: &nbsp;&nbsp; [/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)

The readme.md file located in the [/TeamCode/src/main/java/org/firstinspires/ftc/teamcode](TeamCode/src/main/java/org/firstinspires/ftc/teamcode) folder contains an explanation of the sample naming convention, and instructions on how to copy them to your own project space.

# Release Information

## Version 9.0.1 (20230929-083754)

### Enhancements
* Updates AprilTag samples to include Decimation and additional Comments.  Also corrects misleading tag ID warnings
* Increases maximum size of Blocks inline comments to 140 characters
* Adds Blocks sample BasicOmniOpMode.
* Updated CENTERSTAGE library AprilTag orientation quaternions
    * Thanks [@FromenActual](https://github.com/FromenActual)
* Updated Java Sample ConceptTensorFlowObjectDetection.java to include missing elements needed for custom model support.

### Bug Fixes
* Fixes a problem where after October 1 the Driver Station will report as obsolete on v9.0 and prompt the user to update.

## Version 9.0 (20230830-154348)

### Breaking Changes
* Removes Vuforia
* Fields in `AprilTagDetection` and `AprilTagPose(ftc/raw)` objects are now `final`
* VisionPortal builder method `setCameraMonitorViewId()` has been renamed to `setLiveViewContainerId()` and `enableCameraMonitoring()` has been renamed to `enableLiveView()`

### Enhancements
* Adds support for the DFRobot HuskyLens Vision Sensor.
* Blocks teams can now perform webcam calibration.
    * Added a Block for System.currentTimeMillis (under Utilities/Time)
    * Added a Block for VisionPortal.saveNextFrameRaw (under Vision/VisionPortal)
    * Added a new sample Blocks OpMode called UtilityCameraFrameCapture.
* The RobotDriveByGyro sample has been updated to use the new universal IMU interface.  It now supports both IMU types.
* Removed some error-prone ElapsedTime Blocks from the Blocks editor's toolbox. This is not a
  breaking change: old Blocks OpModes that use these Blocks will still function, both in the
  Blocks editor and at runtime.
* Standardizes on the form "OpMode" for the term OpMode.
    * The preferred way to refer to OpModes that specifically extend `LinearOpMode` (including Blocks OpModes) is "linear OpMode".
    * The preferred way to refer to OpModes that specifically extend `OpMode` directly is "iterative OpMode".
* Overhauls `OpMode` and `LinearOpMode` Javadoc comments to be easier to read and include more detail.
* Makes minor enhancements to Java samples
    * Javadoc comments in samples that could be rendered badly in Android Studio have been converted to standard multi-line comments
    * Consistency between samples has been improved
    * The SensorDigitalTouch sample has been replaced with a new SensorTouch sample that uses the `TouchSensor` interface instead of `DigitalChannel`.
    * The ConceptCompassCalibration, SensorMRCompass, and SensorMRIRSeeker samples have been deleted, as they are not useful for modern FTC competitions.

### Bug Fixes
* Fixes a bug which prevented PlayStation gamepads from being used in bluetooth mode. Bluetooth is NOT legal for competition but may be useful to allow a DS device to be used while charging, or at an outreach event.
* Fixes a bug where a Blocks OpMode's Date Modified value can change to December 31, 1969, if the Control Hub is rebooted while the Blocks OpMode is being edited.
* Fixes the automatic TeleOp preselection feature (was broken in 8.2)
* Fixes a bug where passing an integer number such as 123 to the Telemetry.addData block that takes a number shows up as 123.0 in the telemetry.
* Fixes OnBotJava autocomplete issues:
  * Autocomplete would incorrectly provide values for the current class when autocompleting a local variable
  * `hardwareMap` autocomplete would incorrectly include lambda class entries
* Fixes OnBotJava not automatically importing classes.
* Fixes OnBotJava tabs failing to close when their file is deleted.
* Fixes a project view refresh not happening when a file is renamed in OnBotJava.
* Fixes the "Download" context menu item for external libraries in the OnBotJava interface.
* Fixes issue where Driver Station telemetry would intermittently freeze when set to Monospace mode.
* Fixes performance regression for certain REV Hub operations that was introduced in version 8.2.
* Fixes TagID comparison logic in DriveToTag samples.

## Version 8.2 (20230707-131020)

### Breaking Changes
* Non-linear (iterative) OpModes are no longer allowed to manipulate actuators in their `stop()` method. Attempts to do so will be ignored and logged.
  * When an OpMode attempts to illegally manipulate an actuator, the Robot Controller will print a log message
    including the text `CANCELLED_FOR_SAFETY`.
  * Additionally, LinearOpModes are no longer able to regain the ability to manipulate actuators by removing their
    thread's interrupt or using another thread.
* Removes support for Android version 6.0 (Marshmallow). The minSdkVersion is now 24.
* Increases the Robocol version.
  * This means an 8.2 or later Robot Controller or Driver Station will not be able to communicate with an 8.1 or earlier Driver Station or Robot Controller.
  * If you forget to update both apps at the same time, an error message will be shown explaining which app is older and should be updated.
* FTC_FieldCoordinateSystemDefinition.pdf has been moved.  It is still in the git history, but has been removed from the git snapshot corresponding with the 8.2 tag.  The official version now lives at [Field Coordinate System](https://ftc-docs.firstinspires.org/field-coordinate-system).
* `LynxUsbDevice.addConfiguredModule()` and `LynxUsbDevice.getConfiguredModule()` have been replaced with `LynxUsbDevice.getOrAddModule()`.
* Old Blocks for Vuforia and TensorFlow Object Detection are obsolete and have been removed from the
  Blocks editor's toolbox. Existing Blocks OpModes that contain the old Blocks for Vuforia or
  TensorFlow Object Detection can be opened in the Blocks editor, but running them will not work.

### New features
* Adds new `VisionPortal` API for computer vision
    * **This API may be subject to change for final kickoff release!**
    * Several new samples added.
    * Adds support for detecting AprilTags.
    * `VisionPortal` is the new entry point for both AprilTag and TFOD processing.
    * Vuforia will be removed in a future release.
    * Updated TensorFlow dependencies.
    * Added support for webcam camera controls to blocks.
    * The Blocks editor's toolbox now has a Vision category, directly above the Utilities category.
* Related documentation for associated technologies can be found at
    * [AprilTag Introduction](https://ftc-docs.firstinspires.org/apriltag-intro)
    * [AprilTag SDK Guide](https://ftc-docs.firstinspires.org/apriltag-sdk)
    * [AprilTag Detection Values](https://ftc-docs.firstinspires.org/apriltag-detection-values)
    * [AprilTag Test Images](https://ftc-docs.firstinspires.org/apriltag-test-images)
    * [Camera Calibration](https://ftc-docs.firstinspires.org/camera-calibration)
* Adds Driver Station support for Logitech Dual Action and Sony PS5 DualSense gamepads.
    * This **does not** include support for the Sony PS5 DualSense Edge gamepad.
    * Always refer to Game Manual 1 to determine gamepad legality in competition.
* Adds support for MJPEG payload streaming to UVC driver (external JPEG decompression routine required for use).
* Shows a hint on the Driver Station UI about how to bind a gamepad when buttons are pressed or the sticks are moved on an unbound gamepad.
* Adds option for fullscreening "Camera Stream" on Driver Station.
* OnBotJava source code is automatically saved as a ZIP file on every build with a rolling window of the last 30 builds kept; allows recovering source code from previous builds if code is accidentally deleted or corrupted.
* Adds support for changing the addresses of Expansion Hubs that are not connected directly via USB.
  * The Expansion Hub Address Change screen now has an Apply button that changes the addresses without leaving the screen.
  * Addresses that are assigned to other hubs connected to the same USB connection or Control Hub are no longer able to be selected.
* Increases maximum size of Blocks inline comments to 100 characters
* Saves position of open Blocks comment balloons
* Adds new AprilTag Driving samples:  RobotDriveToAprilTagTank & RobotDriveToAprilTagOmni
* Adds Sample to illustrate optimizing camera exposure for AprilTags: ConceptAprilTagOptimizeExposure

### Bug Fixes
* Corrects inspection screen to report app version using the SDK version defined in the libraries instead of the version specified in `AndroidManifest.xml`. This corrects the case where the app could show matching versions numbers to the user but still state that the versions did not match.
  * If the version specified in `AndroidManifest.xml` does not match the SDK version, an SDK version entry will be displayed on the Manage webpage.
* Fixes no error being displayed when saving a configuration file with duplicate names from the Driver Station.
* Fixes a deadlock in the UVC driver which manifested in https://github.com/OpenFTC/EasyOpenCV/issues/57.
* Fixes a deadlock in the UVC driver that could occur when hot-plugging cameras.
* Fixes UVC driver compatibility with Arducam OV9281 global shutter camera.
* Fixes Emergency Stop condition when an OnBotJava build with duplicate OpMode names occurs.
* Fixes known causes of "Attempted use of a closed LynxModule instance" logspam.
* Fixes the visual identification LED pattern when configuring Expansion Hubs connected via RS-485.
