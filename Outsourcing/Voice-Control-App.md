Voice[Name: en-US-SMTf00, locale: eng_USA, quality: 300, latency: 300, requiresNetwork: false, features: [name=NA, variant=f00, version=202009152, gender=female, uid=null]]
Voice[Name: ko-KR-SMTm01, locale: kor_KOR, quality: 300, latency: 300, requiresNetwork: false, features: [variant=m01, name=NA, version=302105241, gender=male, uid=null]]
Voice[Name: ko-KR-SMTf00, locale: kor_KOR, quality: 300, latency: 300, requiresNetwork: false, features: [name=NA, variant=f00, version=202009152, gender=female, uid=null]]


# 오디오 비디오 합성 ()
ffmpeg -i output.mp4 -i output.mp3 -shortest result.mp4

# 비디오 합성 (https://stackoverflow.com/questions/7333232/how-to-concatenate-two-mp4-files-using-ffmpeg)
ffmpeg -f concat -safe 0 -i files.txt -c copy output-result.mp4


# Open Error file storage 이슈 해결방법
https://exerror.com/filenotfoundexception-open-failed-eperm-operation-not-permitted/
https://stackoverflow.com/questions/61406818/
filenotfoundexception-open-failed-eperm-operation-not-permitted-during-saving

# 이미지 + 오디오 합성하여 비디오 만들기.
ffmpeg -loop 1 -r 1 -i sample.jpg -i output.mp3 -shortest -t 15 -vf "pad=ceil(iw/2)*2:ceil(ih/2)*2" -vcodec libx264 "11.mp4"

# Action Bar Title 변경
- application android:label="@string/app_name" 변경

# Android Studio Key Map 변경 방법


# Swing 간단하게 다이얼로그 띄우는 방법
- JOptionPane.showMessageDialog(frame, "도서가 존재하지 않습니다.");

ffmpeg -i concat:/Users/kimjs/Documents/Image/output.mp4|/Users/kimjs/Documents/Image/result.mp4 -codec copy sssss.mp4

concat:/storage/emulated/0/pdf_to_video/tmp/Sample-0-output.mp4|/storage/emulated/0/pdf_to_video/tmp/Sample-1-output.mp4

ffmpeg version v4.4-dev-416 Copyright (c) 2000-2020 the FFmpeg developers
  built with Android (6454773 based on r365631c2) clang version 9.0.8 (https://android.googlesource.com/toolchain/llvm-project 98c855489587874b2a325e7a516b99d838599c6f) (based on LLVM 9.0.8svn)
  configuration: --cross-prefix=aarch64-linux-android- --sysroot=/files/android-sdk/ndk/21.3.6528147/toolchains/llvm/prebuilt/linux-x86_64/sysroot --prefix=/home/taner/Projects/mobile-ffmpeg/prebuilt/android-arm64/ffmpeg --pkg-config=/usr/bin/pkg-config --enable-version3 --arch=aarch64 --cpu=armv8-a --cc=aarch64-linux-android24-clang --cxx=aarch64-linux-android24-clang++ --extra-libs='-L/home/taner/Projects/mobile-ffmpeg/prebuilt/android-arm64/cpu-features/lib -lndk_compat' --target-os=android --enable-neon --enable-asm --enable-inline-asm --enable-cross-compile --enable-pic --enable-jni --enable-optimizations --enable-swscale --enable-shared --enable-v4l2-m2m --disable-outdev=fbdev --disable-indev=fbdev --enable-small --disable-openssl --disable-xmm-clobber-test --disable-debug --enable-lto --disable-neon-clobber-test --disable-programs --disable-postproc --disable-doc --disable-htmlpages --disable-manpages --disable-podpages --disable-txtpages --disable-static --disable-sndio --disable-schannel --disable-securetransport --disable-xlib --disable-cuda --disable-cuvid --disable-nvenc --disable-vaapi --disable-vdpau --disable-videotoolbox --disable-audiotoolbox --disable-appkit --disable-alsa --disable-cuda --disable-cuvid --disable-nvenc --disable-vaapi --disable-vdpau --enable-libfontconfig --enable-libfreetype --enable-libfribidi --enable-gmp --enable-gnutls --enable-libmp3lame --enable-libass --enable-iconv --enable-libtheora --enable-libvorbis --enable-libvpx --enable-libwebp --enable-libxml2 --enable-libopencore-amrnb --enable-libshine --enable-libspeex --enable-libwavpack --enable-libkvazaar --enable-libilbc --enable-libopus --enable-libsnappy --enable-libsoxr --enable-libaom --enable-libtwolame --disable-sdl2 --enable-libvo-amrwbenc --enable-zlib --enable-mediacodec
  libavutil      56. 55.100 / 56. 55.100
  libavcodec     58. 96.100 / 58. 96.100
  libavformat    58. 48.100 / 58. 48.100
  libavdevice    58. 11.101 / 58. 11.101
  libavfilter     7. 87.100 /  7. 87.100
  libswscale      5.  8.100 /  5.  8.100
  libswresample   3.  8.100 /  3.  8.100
Input #0, image2, from '/storage/emulated/0/pdf_to_video/tmp/Sample-0.jpg':
  Duration: 00:00:00.04, start: 0.000000, bitrate: 23116 kb/s
    Stream #0:0: Video: mjpeg, yuvj420p(pc, bt470bg/unknown/unknown), 595x842 [SAR 1:1 DAR 595:842], 25 fps, 25 tbr, 25 tbn, 25 tbc
Guessed Channel Layout for Input Stream #1.0 : mono
Input #1, wav, from '/storage/emulated/0/pdf_to_video/tmp/Sample-0.mp3':
  Duration: 00:00:00.86, bitrate: 384 kb/s
    Stream #1:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 24000 Hz, mono, s16, 384 kb/s
Invalid duration specification for t: -vf

ffmpeg -loop 1 -r 1 -i /Users/kimjs/Documents/Image/Sample.jpg -i /Users/kimjs/Documents/Image/output.mp3 -shortest -t 15 -vf "pad=ceil(iw/2)*2:ceil(ih/2)*2" -vcodec libx264 /Users/kimjs/Documents/Image/sss.mp4

ffmpeg -loop 1 -r 1 -i /Users/kimjs/Documents/Image/Sample.jpg -i /Users/kimjs/Documents/Image/output.mp3  -shortest -t 15 -vf "pad=ceil(iw/2)*2:ceil(ih/2)*2" -vcodec libx264 /Users/kimjs/Documents/Image/sss.mp4


스프링부트 Test와 Junit Runner의 차이점 ?? 

