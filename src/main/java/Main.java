import core.Core;
import utils.GithubUpdate;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean low_configuration = false;
        if (args.length > 0) {
            if (args[0].equals("low_configuration")) {
                low_configuration = true;
            }
        }

        //new updates
        GithubUpdate.newUpdate();

        Core newCore = new Core(low_configuration);
        newCore.start();

    }
}
