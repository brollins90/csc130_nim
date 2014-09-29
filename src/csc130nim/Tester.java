package csc130nim;

/**
 * Created by Stephen on 9/29/2014.
 * In project: csc130_nim
 */
public class Tester {

    public void testPrompt() {

        Presenter presenter = new Presenter();

        presenter.printOpeningMenu();
        System.out.println(presenter.promptMenuSelection());

    }

}
