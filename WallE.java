package MissMe;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html
// WallE - a robot by (Elayne e Eronildo)
 
public class WallE extends AdvancedRobot{
	public void run() {
		setColors(Color.yellow,Color.black,Color.gray);
		setBulletColor(Color.LIGHT_GRAY);
		setScanColor(Color.gray);
		while(true) {
			setAdjustGunForRobotTurn(true);
			goSafe(150);
			moveGR();
		}
			
	}


	public void onScannedRobot(ScannedRobotEvent e){
		double angle, enemyV = e.getVelocity(), gunH = getGunHeading(), Head = getHeading(),
			enemyB = e.getBearing(), energy = getEnergy(), enemyD = e.getDistance();
		if(enemyV < 4){
			angle = Head - gunH + enemyB;
			turnGunRight(angle);
			if(enemyV==0 && energy>60){
				fire(3);
			}else{
				if(enemyD<70 && energy>60)
					fire(3);
				else
					if(enemyD<150 && energy>40)
						fire(2);
					else
						if(enemyD<500 || enemyV==0)
							fire(1);
			}
		}
		scan();
	}

	
	public void onHitRobot(HitRobotEvent e){
		double angle = getHeading() - getGunHeading() + e.getBearing();
		turnGunRight(angle);
		fire(3);
		if(e.isMyFault()){
			turnLeft(90 - e.getBearing());
			back(150);
		}
		else{
			turnRight(90 - e.getBearing());
			ahead(150);
		}
	}
	
	public void onBulletHit(BulletHitEvent e){
		goSafe(150);
		//scan();		
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		double angle = 90 - e.getBearing();
		setAhead(150);
		turnLeft(angle);
	}
	

	public void onHitWall(HitWallEvent e) {
		turnRight(180 - Math.abs(e.getBearing()));
		ahead(150);
	}
	
	
	
	public void goSafe(int move){
		double X = getX(), Y = getY(), Head = getHeading(),
			Height = getBattleFieldHeight(), Width = getBattleFieldWidth();
		
	 	if(X < 50+move){
	 		if((Y > Height-(50+move)) || (Y >= Height/2)){
	 			if((Head >= 0 && Head < 90) || (Head>=180 && Head<360)){
	 				setAhead(move);
	 				turnRight(180 - Head);
	 			}
	 		}
	 		else{
	 			if(Head>=90 && Head<360){
	 				setAhead(move);
	 				turnRight(180 - Head);
	 			}
	 		}
	 	}
	 	else{
	 		if(X > Width-(50+move)){
	 			if((Y > Height-(50+move)) || (Y >= Height/2)){
	 	 			if((Head>=0 && Head<180) || Head>270){
	 	 				setAhead(move);
	 	 				turnRight(180 - Head);
	 	 			}
	 	 		}
	 	 		else{
	 	 			if(Head > 0 && Head < 270){
	 	 				setAhead(move);
	 	 				turnRight(180 - Head);
	 	 			}
	 	 		}
	 		}
	 		else{
	 			if(X < Width/2){
	 				if(Y > Height-(50+move)){
		 	 			if((Head >= 0 && Head < 90) || (Head>=180 && Head<360)){
		 	 				setAhead(move);
		 	 				turnRight(180 - Head);
		 	 			}
		 	 		}
		 	 		else{
		 	 			if(Y < 50+move){
			 	 			if(Head > 90 && Head < 360){
			 	 				setAhead(move);
			 	 				turnRight(180 - Head);
			 	 			}
		 	 			}
		 	 		}
	 			}
	 			else{
	 				if(Y > Height-(50+move)){
		 	 			if((Head>=0 && Head<180) || Head>270){
		 	 				setAhead(move);
		 	 				turnRight(180 - Head);
		 	 			}
		 	 		}
		 	 		else{
		 	 			if(Y < 50+move){
			 	 			if(Head > 0 && Head < 270){
			 	 				setAhead(move);
			 	 				turnRight(180 - Head);
			 	 			}
		 	 			}
		 	 			else{
				 			setAhead(move);
				 			turnRight(180);
		 	 			}
		 	 		}
	 			}
	 		}
	 	}
	 }

	
	
	public void moveGR(){
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
		turnGunRight(36);
	}
}



