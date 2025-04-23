/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.archimatetool.editor.utils.StringUtils;
import com.archimatetool.script.preferences.IPreferenceConstants;



/**
 * Activitor
 * 
 * @author Phillip Beauvoir
 */
public class ArchiScriptPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "com.archimatetool.script"; //$NON-NLS-1$
    
    // The shared instance
    private static ArchiScriptPlugin instance;

    /**
     * @return the shared instance
     */
    public static ArchiScriptPlugin getInstance() {
        return instance;
    }

    public ArchiScriptPlugin() {
        instance = this;
        
System.out.println("Plugin started");
        
        String startScript = getPreferenceStore().getString(IPreferenceConstants.PREFS_START_SCRIPT);
        if(!StringUtils.isSet(startScript)) {
        	 System.out.println("startScript is not set");
        	 MessageDialog dialog = new MessageDialog(Display.getCurrent().getActiveShell(),
                     "WARNING",
                     null,
                     "Please select startup script in settings",
                     MessageDialog.WARNING,
                     new String[] {
                         IDialogConstants.OK_LABEL,
                         },
                     0);
        	 int result = dialog.open();
        }
        
        File absoluteFile = new File("/Users/vadimrybak/Documents/Archi/scripts/play2.ajs");
        RunArchiScript r = new RunArchiScript(absoluteFile);
        r.run();
    }
    
    /**
     * @return The folder where we store user scripts
     */
    public File getUserScriptsFolder() {
        // Get from preferences
        String path = getPreferenceStore().getString(IPreferenceConstants.PREFS_SCRIPTS_FOLDER);
        
        if(StringUtils.isSet(path)) {
            return new File(path);
        }
        
        // Default
        path = getPreferenceStore().getDefaultString(IPreferenceConstants.PREFS_SCRIPTS_FOLDER);
        return new File(path);
    }
    
}
