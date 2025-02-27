package hust.edu.com.token;

import java.util.HashMap;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class VietToken {
	public static final int WORLD = 1;
	public static final int LAWS = 2;
	public static final int SPORTS = 3;
	public static final int EDUCATION = 4;
	public static final int ENTERTAINMENT = 5;

	private HashMap<String, AprioriInfomation> info;
	private HashMap<String, String> temp;
	private ReadDir dir;
	private StopToken stopWord;
	private VietTokenizer tokenizer;

	public VietToken() {
		info = new HashMap<String, AprioriInfomation>();
		temp = new HashMap<String, String>();
		stopWord = new StopToken();
		tokenizer = new VietTokenizer();
		dir = new ReadDir();
	}

	/**
	 * @param urlDirectory
	 *            : Thu muc chua cac file can doc
	 * @param type
	 *            : Nhan cua cac file
	 * */
	public int process(String urlDirectory, int type) {
		temp = dir.readFilesDirectory(urlDirectory);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String key : temp.keySet()) {
			map = stopWord.accessStopWord(tokenizer.tokenize(temp.get(key))[0]);
			setHashMap(map, key, type);
		}
		return temp.size();
	}

	/**
	 * @param map
	 *            : Key-tu suat hien trong file : Value-so lan suat hien
	 * @param nameFile
	 *            : Ten file
	 * @param type
	 *            : Nhan cua file
	 */
	public void setHashMap(HashMap<String, Integer> map, String nameFile,
			int type) {
		for (String key : map.keySet()) {
			AprioriInfomation ap = info.get(key);
			if (ap == null) {
				ap = new AprioriInfomation(key);
			}
			switch (type) {
			case WORLD:
				ap.addWorld(nameFile, map.get(key));
				break;
			case LAWS:
				ap.addLaws(nameFile, map.get(key));
				break;
			case SPORTS:
				ap.addSports(nameFile, map.get(key));
				break;
			case EDUCATION:
				ap.addEducation(nameFile, map.get(key));
				break;
			case ENTERTAINMENT:
				ap.addEntertainment(nameFile, map.get(key));
				break;
			default:
				break;
			}
			info.put(key, ap);
		}
	}

	/**
	 * @return {@link HashMap<String, AprioriInfomation>} 
	 * 			: Tra ve voi:
	 * 				Key-la tu cua van ban 
	 * 				Value-la kieu du lieu AprioriInfomation chua thong tin cua tu do xuat hien trong file nao, 
	 * 				bao nhieu len,nhan file la gi
	 */
	public HashMap<String, AprioriInfomation> getInfomation() {
		return info;
	}
}
