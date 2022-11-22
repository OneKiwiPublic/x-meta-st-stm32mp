SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
TARGET_CC_ARCH += "${LDFLAGS}"

inherit update-rc.d
INITSCRIPT_NAME = "console-splash-script"
INITSCRIPT_PARAMS = "defaults 90 10"


SRC_URI = "file://console-splash.c"
SRC_URI += "file://console-splash-script"

S = "${WORKDIR}"

do_compile() {
	    ${CC} console-splash.c -o console-splash
}

do_install() {
	    install -d ${D}${bindir}
	    install -m 0755 console-splash ${D}${bindir}

        # Install startup script
	    install -d ${D}/etc/init.d
	    install -m 0755 console-splash-script ${D}/etc/init.d
}

# Add the startup script to the list of files to be packaged.  Any files
# that are installed but not packaged will cause a warning to be printed
# during the bitbake process.
FILES_${PN} += "/etc/init.d/*"