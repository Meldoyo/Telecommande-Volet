#!/bin/sh

docker run --device /dev/mem:/dev/mem --privileged -d -p 80:80 pacordonnier/telecommande-volet
