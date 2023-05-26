package game;

import entity.Entity;
import entity.Plant;
import management.Data;

public class CollisionChecker {
    int entitySolidLeft;
    int entitySolidRight;
    int entitySolidTop;
    int entitySolidBottom;

    ///////////

    int entityLeftCol;
    int entityRightCol;
    int entityTopRow;
    int entityBottomRow;
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        entitySolidLeft = entity.x + entity.solidArea.x;
        entitySolidRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        entitySolidTop = entity.y + entity.solidArea.y;
        entitySolidBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        entityLeftCol = entitySolidLeft / Data.tileSize;
        entityRightCol = entitySolidRight / Data.tileSize;
        entityTopRow = entitySolidTop / Data.tileSize;
        entityBottomRow = entitySolidBottom / Data.tileSize;
    }
    public boolean checkRight(Entity entity) {
        checkTile(entity);
        entityRightCol = (entitySolidRight + entity.speed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
        int tileNum3 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow-1];

        if(entity.y % Data.tileSize != 0) {
            if((tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision) ||
                    (tileNum2 != 9 && gp.tileManager.tile[tileNum2].collision) ||
                    (tileNum3 != 9 && gp.tileManager.tile[tileNum3].collision)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision ||
                    (tileNum3 != 9 && gp.tileManager.tile[tileNum3].collision)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkLeft(Entity entity) {
        checkTile(entity);
        entityLeftCol = (entitySolidLeft - entity.speed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        int tileNum3 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow-1];

        if(entity.y % Data.tileSize != 0) {
            if((tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision) ||
                    (tileNum2 != 9 && gp.tileManager.tile[tileNum2].collision) ||
                    (tileNum3 != 9 && gp.tileManager.tile[tileNum3].collision) ) {
                return false;
            } else {
                return true;
            }
        } else {
            if (tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision ||
                    (tileNum3 != 9 && gp.tileManager.tile[tileNum3].collision)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkUp(Entity entity) {
        checkTile(entity);
        /////////////////////////////////////////az balaye safe rad nashe
        if(entitySolidTop - entity.jumpSpeed < 0) {
            return false;
        }

        entityTopRow = (entitySolidTop - entity.jumpSpeed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];

        if( (tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision) ||
                (tileNum2 != 9 && gp.tileManager.tile[tileNum2].collision) ) {
            return false;
        }
        return true;
    }
    public boolean checkDown(Entity entity) {
        checkTile(entity);
        entityBottomRow = (entitySolidBottom + entity.jumpSpeed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];

        if((tileNum1 != 9 && gp.tileManager.tile[tileNum1].collision) ||
                (tileNum2 != 9 && gp.tileManager.tile[tileNum2].collision) ) {
            return false;
        }
        return true;
    }
    public boolean gravity(Entity entity) {
        checkTile(entity);
        entityBottomRow = (entitySolidBottom + entity.jumpSpeed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];

        if((tileNum1 == 9 || !gp.tileManager.tile[tileNum1].collision) && (tileNum2 == 9 || !gp.tileManager.tile[tileNum2].collision)) {
            return true;
        }
        return false;
    }
    public boolean checkFall(Entity entity) {
        checkTile(entity);
        if(entitySolidBottom >= Data.screenHeight) {
            return true;
        }
        return false;
    }
    public boolean checkPlantCollision(Entity entity, Plant plant) {
        checkTile(entity);
        int plantSolidLeft = plant.realX + plant.solidArea.x;
        int plantSolidRight = plant.realX + plant.solidArea.x + plant.solidArea.width;
        int plantSolidTop = plant.y + plant.solidArea.y;

        if( ( ((plantSolidLeft <= entitySolidLeft) && (entitySolidLeft <= plantSolidRight)) ||
                ((plantSolidLeft <= entitySolidRight) && (entitySolidRight <= plantSolidRight)) ) &&
                (plantSolidTop <= entitySolidBottom) ) {
            return true;
        }
        return false;
    }
    public void checkCoinCollision(Entity entity) {
        checkTile(entity);

        int coinSolidLeft;
        int coinSolidRight;
        int coinSolidTop;
        int coinSolidBottom;

        if(gp.tileManager.mapTileNum[entityLeftCol][entityTopRow] == 8) {
            coinSolidRight = (entityLeftCol * Data.tileSize) + Data.coinX + Data.coinWidth;
            coinSolidBottom = (entityTopRow * Data.tileSize) + Data.coinY + Data.coinHeight;

            if( ( (coinSolidRight - entity.solidArea.width <= entitySolidLeft) && (entitySolidLeft <= coinSolidRight) ) &&
                    ( (coinSolidBottom - entity.solidArea.height <= entitySolidTop) && (entitySolidTop <= coinSolidBottom) ) ) {
                gp.tileManager.mapTileNum[entityLeftCol][entityTopRow] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }

        if(gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow] == 8) {
            coinSolidRight = (entityLeftCol * Data.tileSize) + Data.coinX + Data.coinWidth;
            coinSolidTop = (entityBottomRow * Data.tileSize) + Data.coinY;

            if( ( (coinSolidRight - entity.solidArea.width <= entitySolidLeft) && (entitySolidLeft <= coinSolidRight) ) &&
                    ( (coinSolidTop <= entitySolidBottom) && (entitySolidBottom <= coinSolidTop + entity.solidArea.height) ) ) {
                gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }

        if(gp.tileManager.mapTileNum[entityRightCol][entityTopRow] == 8) {
            coinSolidLeft = (entityRightCol * Data.tileSize) + Data.coinX;
            coinSolidBottom = (entityTopRow * Data.tileSize) + Data.coinY + Data.coinHeight;

            if( ( (coinSolidLeft <= entitySolidRight) && (entitySolidRight <= coinSolidLeft + entity.solidArea.width) ) &&
                    (  (coinSolidBottom - entity.solidArea.height <= entitySolidTop) && (entitySolidTop <= coinSolidBottom) ) ) {
                gp.tileManager.mapTileNum[entityRightCol][entityTopRow] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }

        if(gp.tileManager.mapTileNum[entityRightCol][entityBottomRow] == 8) {
            coinSolidLeft = (entityRightCol * Data.tileSize) + Data.coinX;
            coinSolidTop = (entityBottomRow * Data.tileSize) + Data.coinY;

            if( ( (coinSolidLeft <= entitySolidRight) && (entitySolidRight <= coinSolidLeft + entity.solidArea.width) ) &&
                    ( (coinSolidTop <= entitySolidBottom) && (entitySolidBottom <= coinSolidTop + entity.solidArea.height) ) ) {
                gp.tileManager.mapTileNum[entityRightCol][entityBottomRow] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }



        ////////////////////////////baraye mega va fire

        if(gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow-1] == 8) {
            coinSolidRight = (entityLeftCol * Data.tileSize) + Data.coinX + Data.coinWidth;
            coinSolidTop = ((entityBottomRow-1) * Data.tileSize) + Data.coinY;

            if( ( (coinSolidRight - entity.solidArea.width <= entitySolidLeft) && (entitySolidLeft <= coinSolidRight) ) &&
                    ( (coinSolidTop <= entitySolidBottom-1) && (entitySolidBottom-1 <= coinSolidTop + entity.solidArea.height) ) ) {
                gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow-1] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }

        if(gp.tileManager.mapTileNum[entityRightCol][entityBottomRow-1] == 8) {
            coinSolidLeft = (entityRightCol * Data.tileSize) + Data.coinX;
            coinSolidTop = ((entityBottomRow-1) * Data.tileSize) + Data.coinY;

            if( ( (coinSolidLeft <= entitySolidRight) && (entitySolidRight <= coinSolidLeft + entity.solidArea.width) ) &&
                    ( (coinSolidTop <= entitySolidBottom-1) && (entitySolidBottom-1 <= coinSolidTop + entity.solidArea.height) ) ) {
                gp.tileManager.mapTileNum[entityRightCol][entityBottomRow-1] = 9;
                gp.coins++;
                gp.score += 10;
            }
        }
    }
    public boolean checkSecretPipe(Entity entity) {
        checkTile(entity);
        entityBottomRow = (entitySolidBottom + entity.jumpSpeed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow-1];
        int tileNum3 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow-2];

        if(tileNum1 == 10 || tileNum2 == 10 || tileNum3 == 10) {
            return true;
        }
        return false;
    }
    public boolean checkLevelPassed(Entity entity) {
        checkTile(entity);
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

        if(tileNum1 == 11 || tileNum1 == 12 || tileNum2 == 12) {
            return true;
        }
        return false;
    }
    public void checkBlockCollision(Entity entity) {
        //System.out.println("I am called");
        checkTile(entity);
        entityTopRow = (entitySolidTop - entity.jumpSpeed) / Data.tileSize;
        int tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
        int tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];

        if (tileNum1 == 7) {
            if(entitySolidLeft > (entityLeftCol*Data.tileSize) - (Data.tileSize/2) &&
                    entitySolidLeft < (entityLeftCol*Data.tileSize) + (Data.tileSize/2)) {
                gp.tileManager.mapTileNum[entityLeftCol][entityTopRow] = 9;
            }
        } else if (tileNum2 == 7) {
            if(entitySolidRight > (entityRightCol*Data.tileSize) + (Data.tileSize/2) &&
                    entitySolidRight < (entityRightCol*Data.tileSize) + (3*(Data.tileSize/2))) {
                gp.tileManager.mapTileNum[entityRightCol][entityTopRow] = 9;
            }

        }

    }
}












