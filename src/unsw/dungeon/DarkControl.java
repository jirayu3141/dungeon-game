package unsw.dungeon;


/**
 * Dark mode feature for the game
 * enable dark mode by inputting "type" : "dark" in lv.json 
 *
 */
public class DarkControl {
    private Dungeon dungeon;
    private Player player;
    private boolean init;

    public DarkControl(Dungeon dungeon, Player player){
        this.dungeon = dungeon;
        this.player = player;
        init = false;
    }

    public void setDarkness(){
        String type = dungeon.getType();

        if(type.equals("dark")){
            for (int x = 0; x < dungeon.getWidth(); x++) {
                for (int y = 0; y < dungeon.getHeight(); y++) {

                    if(y != dungeon.getHeight() - 1 
                        && (x != 0 || x != 1 || x != 2 || x != 3) ){
                            Entity darkness = dungeon.getDarkness(x, y);
                            double minX = Math.abs(x - player.getX());
                            double minY = Math.abs(y - player.getY());
                            darkness.changeOpacity(0);
                            if(minX >= minY){
                                if(minX == 0){
                                    darkness.changeOpacity(0);
                                } else{
                                    darkness.changeOpacity((1 - (1/minX)) + 0.15);
                                }
                            } else{
                                darkness.changeOpacity((1 - (1/minY)) + 0.15);
                            }
                        } 
                }
            }

        } else {
            if(!init){
                for (int x = 0; x < dungeon.getWidth(); x++) {
                    for (int y = 0; y < dungeon.getHeight(); y++) {
                        if(y != dungeon.getHeight() - 1 
                        && (x != 0 || x != 1 || x != 2 || x != 3) ){
                            Entity darkness = dungeon.getDarkness(x, y);
                            darkness.changeOpacity(0);
                        } 
                    }
                }
                init = true;
            }
        }
    }
}