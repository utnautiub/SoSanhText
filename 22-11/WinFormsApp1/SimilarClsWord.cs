using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WinFormsApp1
{
    internal class SimilarClsWord
    {
        public static double[] getFeaVector(string str, ref ArrayList fealst)
        {
            str = str.ToLower();

            string text = "text.txt";

            saveToFileUTF8(text, str);
            string[] strlist = getTokTxt(text);

            SimilarCls.getFeature(strlist, ref fealst);
            double[] vector = new double[fealst.Count];

            SimilarCls.calVector(strlist, fealst, ref vector);

            return vector;
        }

        public static void saveToFileUTF8(string fileName, string content)
        {
            StreamWriter streamWriter = new StreamWriter(fileName, append: false, Encoding.UTF8);
            streamWriter.Write(content);
            streamWriter.Close();
        }

        public static string[] getTokTxt(string fileIn)
        {
            string text = "tok.txt";
            string para = "-i " + fileIn + " -o" + text;
            RunExe("vnTokenizer.bat", para);
            string str = readFileUTF8(text);
            return SimilarCls.getFeaArr(str);
        }

        public static string readFileUTF8(string fileName)
        {
            return File.ReadAllText(fileName, Encoding.UTF8);
        }

        public static void RunExe(string filexe, string para)
        {
            ProcessStartInfo processStartInfo = new ProcessStartInfo();
            processStartInfo.CreateNoWindow = false;
            processStartInfo.UseShellExecute = false;
            processStartInfo.Arguments = para;
            processStartInfo.FileName = filexe;
            processStartInfo.WindowStyle = ProcessWindowStyle.Hidden;
            Process process = Process.Start(processStartInfo);
            process.WaitForExit();
        }
    }
}
