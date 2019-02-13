#!/bin/bash
java UDPServer2 | ffmpeg -framerate 25 -f image2pipe -i - -r 25 -f v4l2 -vcodec rawvideo -pix_fmt rgb24 /dev/video1
# you might want to change your framerate or the target device
