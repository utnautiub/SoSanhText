/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.edu.com.naivebayes;

import hust.edu.com.token.StopToken;
import hust.edu.com.voc.MyFile;
import hust.edu.com.voc.VocManagement;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 *
 * @author Hung
 */
public class NaiveBayes {

    private HashMap<String, Integer> mainVocabulary;
    private HashMap<String, Double>[] probability;
    private HashMap<String, Integer>[] vocOfDir;
    private int[] numWordOfDir;
    private int numDir;
    private File[] list;

    public NaiveBayes() {
        mainVocabulary = new HashMap<>();
    }

    public void train(File directory) {
        list = directory.listFiles();
        VocManagement vocManagement = new VocManagement();
        numDir = list.length;
        vocOfDir = new HashMap[list.length];
        numWordOfDir = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            vocOfDir[i] = vocManagement.build(list[i]);// xây dựng từ điển cho 1 thư mục
            Iterator iterator = vocOfDir[i].keySet().iterator();
            //cập nhật từ điển chính
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                if (mainVocabulary.containsKey(key)) {
                    mainVocabulary.put(key, mainVocabulary.get(key) + vocOfDir[i].get(key));
                } else {
                    mainVocabulary.put(key, vocOfDir[i].get(key));
                }
                numWordOfDir[i] += vocOfDir[i].get(key);
            }
        }
        int numWord = mainVocabulary.size();
        probability = new HashMap[list.length];
        for (int i = 0; i < list.length; i++) {
            probability[i] = new HashMap<String, Double>();
        }
        Iterator inIterator = mainVocabulary.keySet().iterator();
        while (inIterator.hasNext()) {
            String key = inIterator.next().toString();
            for (int i = 0; i < list.length; i++) {
                if (vocOfDir[i].containsKey(key)) {
                    probability[i].put(key, (double) (vocOfDir[i].get(key) + 1) / (numWordOfDir[i] + numWord));
                } else {
                    probability[i].put(key, (double) 1 / (numWordOfDir[i] + numWord));
                }
            }
        }

    }

    public HashMap<String, Integer> getMainVoc() {
        return mainVocabulary;
    }

    public int testFile(File file) {
        HashMap<String, Integer> vocOfStr = null;
        vocOfStr = new StopToken().accessStopWord(new VietTokenizer().
                tokenize(new MyFile().readFile(file))[0]);
        int[] score = new int[numDir];
        for (int i = 0; i < numDir; i++) {
            score[i] = 0;
        }
        Iterator iterator = vocOfStr.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            for (int i = 0; i < numDir; i++) {
                if (mainVocabulary.containsKey(key)) {
                    score[i] += Math.log(probability[i].get(key));
                }
            }
        }
        int max = 0;
        for (int i = 0; i < numDir; i++) {
            if (score[max] < score[i]) {
                max = i;
            }
        }
        return max;

    }

    public double[] test(File directory) {
        File[] listDir = directory.listFiles();
        double o[] = new double[5];
        for (int i = 0; i < listDir.length; i++) {
            int numFile = listDir[i].listFiles().length;
            int count = 0;
            for (int j = 0; j < numFile; j++) {
                int label = testFile(listDir[i].listFiles()[j]);
                if (label == i) {
                    count++;
                }
            }
            o[i] = (double) count / numFile;
            System.out.println("thư mục " + listDir[i].getName() + " có tỉ lệ gán nhãn chính xác: " + (double) count / numFile);
        }
        return o;
    }

    public String testString(String str) {
        String label = null;
        HashMap<String, Integer> vocOfStr = getVocOfStr(str);
        double[] score = new double[numDir];
        for (int i = 0; i < numDir; i++) {
            score[i] = 0;
        }
        Iterator iterator = vocOfStr.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            for (int i = 0; i < numDir; i++) {
                if (mainVocabulary.containsKey(key)) {
                    score[i] += Math.log(probability[i].get(key));
                }
            }
        }
        int max = 0;
        for (int i = 0; i < numDir; i++) {
            if (score[max] < score[i]) {
                max = i;
            }
        }
        label = list[max].getName();
        return label;

    }

    public HashMap<String, Integer> getVocOfStr(String str) {
        HashMap<String, Integer> vocOfSet = null;
        vocOfSet = new StopToken().accessStopWord(str);
        return vocOfSet;
    }

    public File[] getListFile() {
        return list;
    }

    public int getNumDir() {
        return numDir;
    }

    public double[] getScore(String str) {
        HashMap<String, Integer> vocOfStr = getVocOfStr(str);
        double[] score = new double[numDir];
        for (int i = 0; i < numDir; i++) {
            score[i] = 0;
        }
        Iterator iterator = vocOfStr.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            for (int i = 0; i < numDir; i++) {
                if (mainVocabulary.containsKey(key)) {
                    score[i] += Math.log(probability[i].get(key));
                }
            }
        }
        return score;
    }

    public static void main(String[] args) {
        NaiveBayes nv = new NaiveBayes();
        nv.train(new File("Resource/Training"));
        nv.test(new File("Resource/Test"));
        String str = new String("Những nữ sinh tài năng của Học viện Kỹ thuật quân sự\n" +
"Trong môi trường toàn nam giới, những nữ sinh viên ít ỏi của Học viện Kỹ thuật quân sự đã cố gắng học hỏi, đạt nhiều thành tích trong nghiên cứu khoa học và tốt nghiệp với tấm bằng loại giỏi.\n" +
"Phần thưởng Nữ sinh tiêu biểu trong lĩnh vực kỹ thuật năm 2014 được trao cho 20 nữ sinh xuất sắc các trường đại học toàn quốc, riêng Học viện Kỹ thuật quân sự có 3 gương mặt giành giải là Lê Thị Xuân (Khoa vô tuyến điện tử), Trần Thị Hồng (Khoa vũ khí) và Vũ Thị Thủy (Khoa cơ khí). Xuân và Hồng là sinh viên hệ quân sự, vừa ra trường với cấp hàm trung úy, còn Thủy học năm cuối hệ dân sự. \n" +
"\n" +
"Tốt nghiệp loại giỏi với số điểm 8,63, Lê Thị Xuân (quê Thanh Hóa) là á khoa đầu ra của Học viện kỹ thuật quân sự. Đối với Xuân, trở thành quân nhân vừa là mơ ước của em, vừa để thực hiện ước mơ chưa thành của bố. Năm đó, Xuân thi đạt 26,5 điểm, vượt qua hàng nghìn hồ sơ của thí sinh nữ khác để vào trường. \n" +
"\n" +
"Con gái học kỹ thuật vất vả, song Xuân tự hào có thể hàn mạch điện tử, chỉnh mạch điện \"ngon lành\" chẳng kém gì con trai. Nữ trung úy nhớ nhất lần cả lớp đi hàn mạch dưới xưởng điện tử. Hôm đó nghiệm thu sản phẩm là chiếc đài radio các nhóm làm. \"Vì cuộn đồng cuốn không chặt, chân mối hàn chưa chắc nên đài không phát ra tiếng nói. Một bạn cuống quá, cấp nhầm nguồn điện khiến chiếc đài nổ tung, khói bay mù mịt\", Xuân nhớ lại.\n" +
"\n" +
"Học viện Kỹ thuật quân sự có rất nhiều môn đặc thù như ăng-ten, siêu cao tần... Đặc biệt là siêu cao tần - môn học được coi là \"ác mộng\" đối với nhiều học viên trong trường. Xuân nghe các anh chị khóa trước nói rằng, có lớp 15 người thì 14 người bị trượt môn này nên ra sức học. Đến khi thi, cô sinh viên lại giành được 10 điểm của thầy Tạ Chí Hiếu, người nổi tiếng nghiêm khắc trong trường.\n" +
"\n" +
"Nhờ điểm 10 đó, Xuân được thầy Nguyễn Quốc Định chú ý và đưa vào nhóm nghiên cứu quy tụ những sinh viên xuất sắc, hướng dẫn làm đề tài khoa học. \"Đó là hai người thầy có ảnh hưởng nhất, trao nhiều cơ hội để em có được thành quả như ngày hôm nay\", Xuân tâm sự.\n" +
"\n" +
"Suốt khóa học, Xuân giành nhiều giải thưởng khoa học của trường và toàn quân, đạt giải ba kỳ thi Olympic Toán sinh viên toàn quốc năm 2011, đặc biệt là làm đồ án thiết kế \"Ăng ten cho thiết bị cầm tay\" có ứng dụng thực tế cao. Cô gái quen \"ăn to nói lớn\" cũng là Chủ tịch Hội phụ nữ K45 của học viện.\n" +
"\n" +
"Cách đây 5 năm, Trần Thị Hồng lần đầu tiên từ Đà Nẵng ra Hà Nội học. Nghe mẹ kể trong quân ngũ có nhiều niềm vui, nữ sinh tin tưởng rằng chỉ cần được khoác áo lính là rất oai nghiêm và hạnh phúc. Cho đến khi trải qua 6 tháng huấn luyện tân binh ở trường Sĩ quan Lục quân 1, Hồng mới dần hiểu để trở thành một người lính đúng nghĩa thì phải rèn luyện rất nhiều.\n" +
"\n" +
"Điểm khởi đầu của quá trình ấy là đôi bàn tay phồng rộp vì cuốc đất để tăng gia, da đen sạm vì quay cuồng dưới nắng gió tập điều lệnh, học chiến thuật; nhiều lúc khóc vì nhớ ba mẹ mà không được gọi điện về nhà. \"Đổi lại, bọn em cũng có nhiều niềm vui không bao giờ quên. Cả khóa học chỉ có 36 nữ học viên, bọn em đùm bọc, thương nhau lắm. Sức khỏe em hơi yếu, có lần tụt huyết áp được các bạn cõng lên phòng y tế, quan tâm từng bữa ăn, giấc ngủ\", Hồng chia sẻ. Đặc biệt, khoa Vũ khí từ trước đến nay toàn con trai, đến khóa của Hồng mới có 3 nữ học viên nên việc gì cũng được các bạn trai nhiệt tình giúp đỡ.\n" +
"\n" +
"Học lớp Khí tài quang, Hồng thích thú vì được tiếp xúc với nhiều loại vũ khí từ thời chiến tranh đến hiện đại. Thấy phần kính ngắm thường bị mốc, tổn thương do nứt vỡ, nữ sinh đã thực hiện đề tài nghiên cứu \"Một số giải pháp phục hồi vật kính gương - thấu kính của kính ngắm đêm PVS - 2 của Mỹ\" và nhận được sự đánh giá cao từ các thầy trong trường.\n" +
"\n" +
"Ngoài học tập xuất sắc, Hồng còn là Phó chủ nhiệm CLB tiếng Anh của Học viện. Tốt nghiệp loại giỏi với số điểm 8,46, nữ trung úy nhận công tác tại Viện kỹ thuật Hải quân, thuộc Bộ Tư lệnh Hải quân Vùng 1 (Hải Phòng). \"Lực lượng hải quân giờ đang phát triển các loại vũ khí, khí tài hiện đại. Em muốn được về viện để tiếp tục nghiên cứu\", Hồng cho biết.\n" +
"\n" +
"Cô sinh viên dân sự Vũ Thị Thủy (sinh 1992) cũng là một trong những gương mặt tài năng của Học viện. Cả khoa Cơ khí, chỉ có Thủy và một bạn nữa là học viên nữ. \"Con gái học ngành cơ khí thì hiếm lắm, bọn em phải đứng máy thực hành, cầm que hàn, xuống xưởng như những bạn nam khác\", nữ sinh quê Thái Bình cho hay.\n" +
"\n" +
"Công việc khó, song lại thú vị nhất với con gái học cơ khí là mỗi lần cầm que hàn ghép những tấm kim loại với nhau, nhiệt độ lên đến 7.000 độ C, tất cả phải tuân thủ nghiêm ngặt quy trình an toàn lao động. Có niềm say mê đặc biệt với những mối hàn, Thủy cùng với người bạn học thiết kế ra \"Máy làm sạch mối hàn bằng phương pháp điện hóa\" và giành giải khuyến khích cuộc thi Tài năng khoa học trẻ Việt Nam năm 2014.\n" +
"\n" +
"Hơn 5 năm học dưới mái trường Học viện Kỹ thuật quân sự đã cho các nữ sinh động lực phấn đấu và những kỷ niệm khó phai mờ. Với Thủy, đó là sự chuyển biến từ nuối tiếc, thất vọng khi không đủ điểm vào hệ quân sự, phải học hệ dân sự dần yêu thích ngành cơ khí hơn. Dù chưa tốt nghiệp, Thủy đã đứng lớp dạy thêm cách vận hành máy tiện CNC cho lao động chuẩn bị xuất khẩu. Cô tiếp tục phấn đấu để giữ vững danh hiệu 5 năm liên tục là học viên giỏi, xuất sắc của trường.\n" +
"\n" +
"Tươi tắn trong màu quân phục, Xuân chờ nhận công tác về Binh chủng thông tin. Từ điểm 10 môn học siêu cao tần năm ấy, được các thầy giỏi hướng dẫn, từ học viên trung bình Xuân phấn đấu lên mức giỏi. Cô nghiệm ra một điều \"Cuộc sống không có cái gì tự nhiên đến, tất cả có được nhờ quá trình cố gắng không nghỉ\".\n" +
"\n" +
"Sinh ra ở Đà Nẵng, có nguyện vọng thi vào miền Nam, cuối cùng lại đi học ở Hà Nội và làm việc ở Hải Phòng, cuộc sống đối với Hồng là quá trình hòa nhập liên tục với môi trường mới để trưởng thành. Từ một nữ sinh yếu đuối, Hồng trở nên quyết đoán, tự chọn đường đi cho mình. Dù lựa chọn ấy khiến mẹ buồn khóc vì thương con gái phải xa nhà.\n" +
"\n" +
"\"Sinh viên quân đội sống trong môi trường gò bó, ít được tiếp xúc với bên ngoài như các bạn học dân sự. Trong trường, các thầy cô giáo vừa là thầy, vừa là cha anh, vừa dạy kiến thức cũng là người truyền kinh nghiệm, kỹ năng sống cho bọn em. Các thầy luôn dặn rằng đến môi trường mới phải luôn hòa nhập, đừng bao giờ mang mác học viện to đùng mà kiêu ngạo, không chịu phấn đấu\", Hồng nhắc lại lời thầy dặn trước ngày rời học viện.");
        System.out.println("-------------------------------------");
        System.out.println(" nhãn cuả đoạn text: " + nv.testString(str));
        double[] score = nv.getScore(str);
        for (int i = 0; i < nv.getNumDir(); i++) {
            System.out.println("nhãn "
                    + nv.getListFile()[i].getName() + " có điểm: " + nv.getScore(str)[i]);
        }
        HashMap<String, Integer> vocOfStr = nv.getVocOfStr(str);
        Iterator iterator = vocOfStr.keySet().iterator();
        System.out.println("tập key:");
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            System.out.println(key + ": " + vocOfStr.get(key));
        }
    }
}
