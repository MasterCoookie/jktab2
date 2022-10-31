/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.jktab;

import pl.polsl.jktab.Controllers.TabController;
import pl.polsl.jktab.Models.Tab;
import pl.polsl.jktab.Views.TabView;


/**
 *
 * @author JK
 * @version p2.0
 */
public class Jktab {
    /**
    * Main program method 
    * @param args command line arguments, 1st - TAB username, 2nd - contact info
    */
    public static void main(String[] args) {
       
        TabView tabView = new TabView();
        Tab tab = new Tab();
        TabController tabController = new TabController(tab, tabView);
        
        tabController.handleUserArgs(args);
        
        tabController.startup();
    }
}
