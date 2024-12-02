using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace WinFormsApp1
{
    internal class SimilarCls
    {
        public static double[] getFeaVector2AmTiet (string str, ref ArrayList fealst)
        {
            str = str.ToLower ();
            string[] strlist = getFeaArr(str);

            getFeature(strlist, ref fealst);

            double[] vector = new double[fealst.Count];
            calVector(strlist, fealst, ref vector);

            return vector;
        }

        public static string[] getFeaArr(string str)
        {
            return str.Split(' ', '-', '"', '\'', '?', ',', ':', ';', '.', '(', ')');
        }

        public static void getFeature(string[] strlist, ref ArrayList fealst)
        {
            foreach (string text in strlist)
            {
                string text2 = text.Trim();
                if (text2 != "" && checkFea(text2, fealst) < 0)
                {
                    fealst.Add(text2);
                }
            }
        }

        public static int checkFea(string str, ArrayList fealst)
        {
            if (fealst.Count > 0)
            {
                for (int i = 0; i < fealst.Count; i++)
                {
                    string text = (string)fealst[i];
                    if (str == text)
                    {
                        return i;
                    }
                }
            }
            return -1;
        }

        public static void calVector(string[] strlist, ArrayList fealst, ref double[] vector)
        {
            for (int i = 0; i < fealst.Count; i++)
            {
                vector[i] = 0.0;
                string text = (string)fealst[i];
                foreach (string text2 in strlist)
                {
                    if ( text == text2.Trim())
                    {
                        vector[i] += 1.0;
                    }
                }
            }
        }

        public static ArrayList unifyFealst(ArrayList fealst1, ArrayList fealst2)
        {
            ArrayList arrayList = new ArrayList();
            foreach (object item in fealst1)
            {
                arrayList.Add(item);
            }

            foreach (object item2 in fealst2)
            {
                string text = (string)item2;
                if (text != "" && checkFea(text, arrayList) < 0)
                {
                    arrayList.Add(item2);
                }
            }
            return arrayList;
        }

        public static double calSimilarCosineAllFea(ArrayList allFealst, ArrayList fealst1, ArrayList fealst2, double[] vector1, double[] vector2)
        {
            double[] array = new double[allFealst.Count];
            double[] array2 = new double[allFealst.Count];
            for (int i = 0; i < allFealst.Count; i++)
            {
                array[i] = FindFeaVal(allFealst[i], fealst1, vector1);
                array2[i] = FindFeaVal(allFealst[i], fealst2, vector2);
            }

            return calCosine(array, array2, allFealst.Count);
        }

        public static double FindFeaVal (object obj, ArrayList fealst, double[] vector)
        {
            int num = checkFea((string)obj, fealst);
            if (num >=0)
            {
                return vector[num];
            }
            return 0.0;
        }

        public static double calCosine(double[] vector1, double[] vector2, int count) {
            double num = 0.0;
            double num2 = 0.0;
            double num3 = 0.0;
            for (int i = 0; i < count; i++) { 
                num += vector1[i] * vector2[i];
                num2 += Math.Pow(vector1[i], 2.0);
                num3 += Math.Pow(vector2[i], 2.0);
            }

            return num/ Math.Sqrt(num2*num3);
        }
    }
}
