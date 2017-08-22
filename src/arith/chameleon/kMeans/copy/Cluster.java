package arith.chameleon.kMeans.copy;

import java.util.Random;
import java.util.Scanner;

class Point {
	
	public float x = 0;
	public float y = 0;
	public int flage = -1;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}

public class Cluster {
 	
	Point[] ypo;// �㼯
	Point[] pacore = null;// old�������� ^\s*\d+\.
	Point[] pacoren = null;// new��������

	// ���Ծ������ģ��㼯
	public void productpoint() {
		Scanner cina = new Scanner(System.in);
		System.out.print("����������е�ĸ���������");
		int num = cina.nextInt();

		ypo = new Point[num];
		// �������
		for (int i = 0; i < num; i++) {

			float x = (int) (new Random().nextInt(10));
			float y = (int) (new Random().nextInt(10));

			ypo[i] = new Point();// ���󴴽�
			ypo[i].setX(x);
			ypo[i].setY(y);

		}

		// ��ʼ����������λ��
		System.out.print("�������ʼ���������ĸ���������");
		int core = cina.nextInt();
		this.pacore = new Point[core];// ��ž�������
		this.pacoren = new Point[core];

		Random rand = new Random();
		int temp[] = new int[core];
		temp[0] = rand.nextInt(num);
		pacore[0] = new Point();
		pacore[0] = ypo[temp[0]];
		// ��������ظ�������
		for (int i = 1; i < core; i++) {
			int flage = 0;
			int thistemp = rand.nextInt(num);
			for (int j = 0; j < i; j++) {
				if (temp[j] == thistemp) {
					flage = 1;// ���ظ�
					break;

				}
			}
			if (flage == 1) {
				i--;
			} else {
				pacore[i] = new Point();
				pacore[i] = ypo[thistemp];
				pacore[i].flage = 0;// 0��ʾ��������
			}

		}
		System.out.println("��ʼ�������ģ�");
		for (int i = 0; i < pacore.length; i++) {
			System.out.println(pacore[i].x + " " + pacore[i].y);
		}

	}

	// ///�ҳ�ÿ���������ĸ���������
	public void searchbelong()// �ҳ�ÿ���������ĸ���������
	{

		for (int i = 0; i < ypo.length; i++) {
			double dist = 999;
			int lable = -1;
			for (int j = 0; j < pacore.length; j++) {

				double distance = distpoint(ypo[i], pacore[j]);
				if (distance < dist) {
					dist = distance;
					lable = j;
					// po[i].flage = j + 1;// 1,2,3......

				}
			}
			ypo[i].flage = lable + 1;

		}

	}

	// ���¾�������
	public void calaverage() {

		for (int i = 0; i < pacore.length; i++) {
			System.out.println("��<" + pacore[i].x + "," + pacore[i].y
					+ ">Ϊ���ĵĵ㣺");
			int numc = 0;
			Point newcore = new Point();
			for (int j = 0; j < ypo.length; j++) {

				if (ypo[j].flage == (i + 1)) {
					numc += 1;
					newcore.x += ypo[j].x;
					newcore.y += ypo[j].y;
					System.out.println(ypo[j].x + "," + ypo[j].y);
				}
			}
			// �µľ�������
			pacoren[i] = new Point();
			pacoren[i].x = newcore.x / numc;
			pacoren[i].y = newcore.y / numc;
			pacoren[i].flage = 0;
			System.out.println("�µľ������ģ�" + pacoren[i].x + "," + pacoren[i].y);

		}
	}

	public double distpoint(Point px, Point py) {

		return Math.sqrt(Math.pow((px.x - py.x), 2)
				+ Math.pow((px.y - py.y), 2));

	}

	public void change_oldtonew(Point[] old, Point[] news) {
		for (int i = 0; i < old.length; i++) {
			old[i].x = news[i].x;
			old[i].y = news[i].y;
			old[i].flage = 0;// ��ʾΪ�������ĵı�־��
		}
	}

	public void movecore() {
		// this.productpoint();//��ʼ���������������ģ�
		this.searchbelong();
		this.calaverage();//
		double movedistance = 0;
		int biao = -1;// ��־���������ĵ���ƶ��Ƿ�����С����
		for (int i = 0; i < pacore.length; i++) {
			movedistance = distpoint(pacore[i], pacoren[i]);
			System.out.println("distcore:" + movedistance);// �������ĵ��ƶ�����
			if (movedistance < 0.01) {
				biao = 0;

			} else {
				biao = 1;
				break;

			}
		}
		if (biao == 0) {
			System.out.print("�����ϣ���������");
		} else {
			change_oldtonew(pacore, pacoren);
			movecore();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cluster kmean = new Cluster();
		kmean.productpoint();
		kmean.movecore();
	}
 	   
}
