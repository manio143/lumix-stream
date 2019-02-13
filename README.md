# Lumix GH3 stream package

If you have a Lumix GH3 camera you can stream a 640x480 image to your PC via WiFi.

The code in this repo is based on work of others <https://www.dpreview.com/forums/thread/3749408>

My setup is based on two WiFi cards one for connecting to the GH3 and one for connecting to the Internet.

Run `make` to compile the Java application.

You'll also need to build and install the [akvcam](https://github.com/webcamoid/akvcam) and install [ffmpeg](https://www.ffmpeg.org/).

You can use my slightly modified default config for akvcam `akvcam_config.ini`.

If your camera is in a different subnet then 192.168.0.0 then you might have to change all the IP addreses in `requests.html`.

You might want to modify the `run.sh` depending on akvcam config and camera framerate.

1. Load the akvcam kernel module
2. Turn on WiFi on the GH3 and connect to it
3. Run `Control.html` in a browser (keep it open)
4. Run `run.sh`

This will stream the images (that come from camera as JPGs over UDP) into ffmpeg that will convert them into a videofile and supply it to akvcam.

In total it adds about 650ms delay to the image.
