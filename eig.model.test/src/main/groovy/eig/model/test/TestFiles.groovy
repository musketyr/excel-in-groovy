package eig.model.test

import groovy.util.logging.Log

import java.awt.Desktop

@Log
class TestFiles {

    /**
     * Tries to open the file in Word. Only works locally on Mac at the moment. Ignored otherwise.
     * Main purpose of this method is to quickly open the generated file for manual review.
     * @param file file to be opened
     */
    static void open(File file, long sleepTime = 10000) {
        try {
            if (Desktop.desktopSupported && Desktop.desktop.isSupported(Desktop.Action.OPEN)) {
                Desktop.desktop.open(file)
                Thread.sleep(sleepTime)
            }
        } catch(e) {
            log.warning(String.valueOf(e))
        }
    }

    static File newTestFile(String name) {
        File folder = new File(System.getProperty("user.home"), 'excel-in-groovy')
        if (!folder.exists()) {
            folder.mkdir()
        }
        return new File(folder, name)
    }

}
