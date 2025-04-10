SUMMARY = "This is the set of GNU shar utilities."
HOMEPAGE = "http://www.gnu.org/software/sharutils/"
SECTION = "console/utils"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit gettext autotools update-alternatives

SRC_URI = "${GNU_MIRROR}/${BPN}/${BP}.tar.gz \
           file://0001-Fix-build-with-clang.patch \
           file://CVE-2018-1000097.patch \
           file://0001-Fix-build-with-recent-gettext.patch \
           file://0001-Fix-building-with-GCC-10.patch \
           file://0002-Do-not-include-lib-md5.c-into-src-shar.c.patch \
           file://0001-configure.ac-Check-and-define-intmax_t-type.patch \
           file://0001-libopts.m4-accept-POSIX_SHELL-from-the-environment-d.patch \
           file://0001-ISO-C23-Backport-stdbool.m4.patch \
           file://0002-ISO-C23-Port-getcwd.m4-to-ISO-C23.patch \
           file://0003-ISO-C23-Port-the-code-to-ISO-C23.patch \
           "
SRC_URI[sha256sum] = "ee336e68549664e7a19b117adf02edfdeac6307f22e5ba78baca457116914637"

EXTRA_OECONF = "POSIX_SHELL=${base_bindir}/sh"

do_install:append() {
    if [ -e ${D}${libdir}/charset.alias ]
    then
        rm -rf ${D}${libdir}/charset.alias
        rmdir --ignore-fail-on-non-empty ${D}${libdir}
    fi
}

BBCLASSEXTEND = "native nativesdk"

ALTERNATIVE:${PN} = "uudecode uuencode"
ALTERNATIVE_LINK_NAME[uudecode] = "${bindir}/uudecode"
ALTERNATIVE_LINK_NAME[uuencode] = "${bindir}/uuencode"
