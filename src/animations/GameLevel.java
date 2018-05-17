package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import levels.LevelInformation;
import collidables.Collidable;
import collidables.GameEnvironment;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Counter;
import sprites.SpriteCollection;
import sprites.Paddle;
import sprites.DeathBlock;
import sprites.ScoreIndicator;
import sprites.LiveIndicator;
import sprites.Sprite;
import sprites.BoundaryBlock;
import sprites.Block;
import java.awt.Color;
import java.util.List;
import sprites.IndicatorsBlock;
/**
 * Created by Eilon.
 * ID: 308576933.
 * GameLevel class, initialize the game by the level information it gets.
 */
public class GameLevel implements Animation {
    //Members
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle gamePaddle;
    private DeathBlock deathRegion;
    private Ball[] balls;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private Counter livesCounter;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation levelInfo;
    private ScoreIndicator scoreIndicator;
    private LiveIndicator liveIndicator;

    /**
     * GameLevel constructor.
     *
     * @param levelInfo information about the level.
     * @param ks        keyboard sensor.
     * @param ar        animation runner.
     * @param score     score indicator.
     * @param lives     lives indicator.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar,
                     ScoreIndicator score, LiveIndicator lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInfo;
        this.keyboard = ks;
        this.runner = ar;
        this.scoreIndicator = score;
        this.liveIndicator = lives;
        this.livesCounter = liveIndicator.getCounter();
        this.scoreCounter = this.scoreIndicator.getCounter();
        this.addSprite(this.levelInfo.getBackground());
    }

    /**
     * addCollidable.
     * adds a Collidable object to the list.
     *
     * @param c colliadable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite.
     * adds a Sprite object to the list.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * getGuiHeight.
     *
     * @return screen's height.
     */
    public int getGuiHeight() {
        return 600;
    }

    /**
     * getGuiwidth.
     *
     * @return screen's width.
     */
    public int getGuiWidth() {
        return 800;
    }

    /**
     * createPaddle.
     * The method gets the speead and the width of the paddle from the game level
     * information and creates new paddle, the it adds it to the game.
     *
     * @param speed speed of the paddle.
     * @param width width of the paddle.
     * @return paddle.
     */
    public Paddle createPaddle(int speed, int width) {
        Paddle paddle = new Paddle(this, this.keyboard, width,
                (0.025 * this.getGuiHeight()), Color.ORANGE, speed);
        paddle.addToGame(this);
        return paddle;
    }

    /**
     * setBall.
     * creates ball.
     *
     * @param startBalls the point the ball should start.
     * @param numOfBalls how many balls to create.
     * @return ball.
     */
    public Ball[] setBalls(Point startBalls, int numOfBalls) {
        Ball[] ballsArr = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            ballsArr[i] = new Ball(startBalls, 6, Color.WHITE);
            ballsArr[i].setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ballsArr[i].setGameEnvironment(this.environment);
            ballsArr[i].addToGame(this);
        }
        return ballsArr;
    }

    /**
     * setBlockAsBoundaries.
     * creates the blocks for the boundaries.
     *
     * @param lim1      upper left point of the screen.
     * @param lim2      upper right point of the screen.
     * @param guiWidth  screen's width.
     * @param guiHeight screen's height.
     * @param color     color of the blocks.
     * @return returns the blocks in array.
     */
    public BoundaryBlock[] setBlockAsBoundaries(Point lim1, Point lim2, int guiWidth,
                                                int guiHeight, Color color) {
        BoundaryBlock[] blocksArr = new BoundaryBlock[3];
        blocksArr[0] = new BoundaryBlock(lim1, 20, guiHeight, color);
        blocksArr[1] = new BoundaryBlock(lim1, guiWidth, 20, color);
        blocksArr[2] = new BoundaryBlock(lim2, 20, guiHeight, color);
        for (BoundaryBlock b : blocksArr) {
            b.setNumberOnBlock(0);
            b.addToGame(this);
        }
        return blocksArr;
    }

    /**
     * blockListeners.
     * The method gets list of blocks and add the listener to all of them.
     *
     * @param blocksArr list of blocks.
     * @param listener  hit listener.
     */
    public void blocksListeners(List<Block> blocksArr, HitListener listener) {
        for (Block b : blocksArr) {
            b.addHitListener(listener);
        }
    }

    /**
     * initialize.
     * Initialize a new game: creates the listeners and the counters, the boundaries and the
     * death region.
     */
    public void initialize() {
        //Counters and listeners.
        this.blocksCounter = new Counter(this.levelInfo.numberOfBlocksToRemove());
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(scoreCounter);
        BoundaryBlock indiBlock = new BoundaryBlock(0, 0, this.getGuiWidth(), 20, Color.GRAY);
        IndicatorsBlock indicatorsBlock = new IndicatorsBlock(indiBlock, liveIndicator,
                scoreIndicator, this.levelInfo.levelName());
        this.sprites.addSprite(scoreIndicator);
        this.sprites.addSprite(liveIndicator);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        //Sets borders.
        Point limPoint1 = new Point(0, 20);
        Point limPoint2 = new Point(0, this.getGuiHeight() - 20);
        Point limPoint3 = new Point(this.getGuiWidth() - 20, 40);
        //Creates blocks for boundaries.
        BoundaryBlock[] blocksAsBoundaries = setBlockAsBoundaries(limPoint1, limPoint3,
                this.getGuiWidth(), this.getGuiHeight(), Color.GRAY);
        this.sprites.addSprite(indicatorsBlock);
        //Get the blocks.
        List<Block> blocks = this.levelInfo.blocks();
        for (Block b : blocks) {
            b.addToGame(this);
        }
        //Death region.
        this.deathRegion = new DeathBlock(limPoint2, this.getGuiWidth(), 25, Color.BLACK);
        this.addCollidable(this.deathRegion);
        this.addSprite(this.deathRegion);
        this.blocksListeners(blocks, blockRemover);
        this.blocksListeners(blocks, scoreTrack);
    }

    /**
     * Sets the sprites and does the countdown animation, then runs it until the user
     * looses life or ends the level.
     */
    public void playOneTurn() {
        this.setSpritesToGame();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        while (!this.shouldStop()) {
            this.runner.run(this);
        }
        this.endPlayOneTurn();
        return;
    }

    /**
     * setSpritesToGame.
     * Creates the balls and the game paddle and adds them to the game.
     * Also adds the listener BallRemover to the death region.
     */
    public void setSpritesToGame() {
        int numOfBalls = this.levelInfo.numberOfBalls();
        this.ballCounter = new Counter(numOfBalls);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        //Creates paddle.
        this.gamePaddle = createPaddle(this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
        //Creates balls.
        Point startBalls = new Point(this.gamePaddle.getCenterPaddlePoint().getX(),
                this.gamePaddle.getCenterPaddlePoint().getY() - 20);
        this.balls = setBalls(startBalls, numOfBalls);
        this.deathRegion.addHitListener(ballRemover);
    }

    /**
     * endPlayOneTurn.
     * After we finish our one turn we need to remove all the old sprites, and checks if the
     * player deserve extra 100 points after removing all the blocks.
     */
    public void endPlayOneTurn() {
        if (this.blocksCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
        }
        this.removeSprite(this.gamePaddle);
        this.removeCollidable(this.gamePaddle);
        for (Ball b : this.balls) {
            b.removeFromGame(this);
        }
    }

    /**
     * doOneFrame.
     * Draws all the sprites in the game, checks if the player finished to
     * remove all the blocks and if he lose one life.
     * Runs the PauseScreen if the player press "p".
     * @param dt 1 / framespersecond
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //Draws all the sprites on list.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.sprites)));
        }
        if (this.blocksCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.livesCounter.decrease(1);
            this.running = false;
        }
    }

    /**
     * shouldStop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * removeCollidable.
     * removes a given collidable from the game.
     *
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite.
     * removes a given sprite from the sprite collection.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * getNumOfBlocks.
     *
     * @return returns how many blocks left in the game.
     */
    public int getNumOfBlocks() {
        return this.blocksCounter.getValue();
    }

    /**
     * getNumOfBalls.
     *
     * @return returns how many balls on the screen.
     */
    public int getNumOfBalls() {
        return levelInfo.numberOfBalls();
    }
}