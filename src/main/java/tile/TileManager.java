package tile;

import entity.Plant;
import management.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    game.GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(game.GamePanel gp) {
        this.gp = gp;

        tile = new Tile[20];
        mapTileNum = new int[Data.maxLevelCol][Data.maxScreenRow+2];

        getTileImage();
        loadMap("/levels/level01.txt");
    }

    public void getTileImage() {

        try {
            ///////////pipes that have plants start with this
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/UpLeftPipe.png"));

            ///////////pipes that go to secret page start with this
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/UpLeftPipe.png"));

            ///////////ground with grass
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/GroundWithGrass.png"));

            //////////////pipe
            //////////////////////up left
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/UpLeftPipe.png"));
            //////////////////////up right
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/UpRightPipe.png"));
            //////////////////////left
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/LeftPipe.png"));
            //////////////////////right
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/RightPipe.png"));


            /////////////question
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/Question.png"));

            ////////////block
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/Block.png"));

            ///////////coin
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/Coin5.png"));
            tile[8].collision = false;


            ////////////flag
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/Flaggg.png"));
            tile[11].collision = false;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tileImages/Flagg2.png"));
            tile[12].collision = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;

            while(row < Data.maxScreenRow) {
                String nextLine = br.readLine();

                while(column < Data.maxLevelCol) {
                    String numbers[] = nextLine.split(" ");

                    mapTileNum[column][row] = Integer.parseInt(numbers[column]);
                    if(mapTileNum[column][row] == 0) {
                        Plant thisPlant = new Plant(column * Data.tileSize, (row + 1) * Data.tileSize);
                        thisPlant.realX = column * Data.tileSize;
                        thisPlant.plantsTile = new int[2];
                        thisPlant.plantsTile[0] = column;
                        thisPlant.plantsTile[1] = row;
                        gp.plants.add(thisPlant);
                    }

                    column++;
                }

                column = 0;
                row++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int levelColumn = 0;
        int levelRow = 0;
        int y = 0;

        while(levelColumn < Data.maxLevelCol && levelRow < Data.maxScreenRow) {

            ////////////// ba if
            if (gp.player.x < gp.player.screenX) {
                int levelX = levelColumn * Data.tileSize;
                if(levelX - Data.tileSize < Data.screenWidth) {

                    ///////////////////////////////////////////////////////////////////////////////
                    if(mapTileNum[levelColumn][levelRow] == 0) {
                        for(Plant plant : gp.plants) {
                            if(plant.plantsTile[0]==levelColumn && plant.plantsTile[1]==levelRow) {
                                plant.draw(g2,levelX);
                                plant.x = levelX;
                            }
                        }
                    }

                    if(mapTileNum[levelColumn][levelRow] != 9) {
                        g2.drawImage(tile[mapTileNum[levelColumn][levelRow]].image, levelX,y, Data.tileSize, Data.tileSize,null);
                    }

                }
                levelColumn++;
                if(levelColumn == Data.maxLevelCol) {
                    levelColumn = 0;
                    levelRow++;
                    y += Data.tileSize;
                }
            } else if(gp.player.x > Data.maxLevelWidth - Data.screenWidth + gp.player.screenX) {

                int levelX = levelColumn * Data.tileSize;
                int drawX = levelX - Data.maxLevelWidth + Data.screenWidth;
                if(levelX + Data.tileSize > Data.maxLevelWidth - Data.screenWidth) {

                    ///////////////////////////////////////////////////////////////////////////////
                    if(mapTileNum[levelColumn][levelRow] == 0) {
                        for(Plant plant : gp.plants) {
                            if(plant.plantsTile[0]==levelColumn && plant.plantsTile[1]==levelRow) {
                                plant.draw(g2,drawX);
                                plant.x = drawX;
                            }
                        }
                    } else if(mapTileNum[levelColumn][levelRow] == 3 && mapTileNum[levelColumn - 1][levelRow] == 0) {
                        for(Plant plant : gp.plants) {
                            if(plant.plantsTile[0]==levelColumn-1 && plant.plantsTile[1]==levelRow) {
                                plant.draw(g2,drawX-Data.tileSize);
                                plant.x = drawX - Data.tileSize;

                                g2.drawImage(tile[mapTileNum[levelColumn-1][levelRow]].image,drawX-Data.tileSize,y, Data.tileSize, Data.tileSize,null);
                                g2.drawImage(tile[mapTileNum[levelColumn-1][levelRow+1]].image,drawX-Data.tileSize,y+Data.tileSize, Data.tileSize, Data.tileSize,null);
                            }
                        }
                    }


                    if(mapTileNum[levelColumn][levelRow] != 9) {
                        g2.drawImage(tile[mapTileNum[levelColumn][levelRow]].image,drawX,y, Data.tileSize, Data.tileSize,null);
                    }




                }
                levelColumn++;
                if(levelColumn == Data.maxLevelCol) {
                    levelColumn = 0;
                    levelRow++;
                    y += Data.tileSize;
                }

            } else {
                int levelX = levelColumn * Data.tileSize;
                int drawX = levelX - gp.player.x + gp.player.screenX;
                if(levelX + Data.tileSize > gp.player.x - gp.player.screenX && levelX - Data.tileSize < gp.player.x - gp.player.screenX + Data.screenWidth) {

                    ///////////////////////////////////////////////////////////////////////////////
                    if(mapTileNum[levelColumn][levelRow] == 0) {
                        for(Plant plant : gp.plants) {
                            if(plant.plantsTile[0]==levelColumn && plant.plantsTile[1]==levelRow) {
                                plant.draw(g2,drawX);
                                plant.x = drawX;
                            }
                        }
                    } else if(mapTileNum[levelColumn][levelRow] == 3 && mapTileNum[levelColumn - 1][levelRow] == 0) {
                        for(Plant plant : gp.plants) {
                            if(plant.plantsTile[0]==levelColumn-1 && plant.plantsTile[1]==levelRow) {
                                plant.draw(g2,drawX-Data.tileSize);
                                plant.x = drawX - Data.tileSize;

                                g2.drawImage(tile[mapTileNum[levelColumn-1][levelRow]].image,drawX-Data.tileSize,y, Data.tileSize, Data.tileSize,null);
                                g2.drawImage(tile[mapTileNum[levelColumn-1][levelRow+1]].image,drawX-Data.tileSize,y+Data.tileSize, Data.tileSize, Data.tileSize,null);
                            }
                        }
                    }


                    if(mapTileNum[levelColumn][levelRow] != 9) {
                        g2.drawImage(tile[mapTileNum[levelColumn][levelRow]].image,drawX,y, Data.tileSize, Data.tileSize,null);
                    }


                }
                levelColumn++;
                if(levelColumn == Data.maxLevelCol) {
                    levelColumn = 0;
                    levelRow++;
                    y += Data.tileSize;
                }

            }
        }
    }
}
