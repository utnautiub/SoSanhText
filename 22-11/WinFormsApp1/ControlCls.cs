using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace WinFormsApp1
{
    internal class ControlCls
    {
        public static double calSimilar2AmTiet( string str1, string str2)
        {
            ArrayList fealst = new ArrayList();
            double[] feaVector = SimilarCls.getFeaVector2AmTiet(str1, ref fealst);

            ArrayList fealst2 = new ArrayList();
            double[] feaVector2 = SimilarCls.getFeaVector2AmTiet(str2, ref fealst2);

            ArrayList allFealst = SimilarCls.unifyFealst(fealst, fealst2);

            return SimilarCls.calSimilarCosineAllFea(allFealst, fealst, fealst2, feaVector, feaVector2) * 100.0;
        }

        public static double calSimilar(string str1, string str2)
        {
            ArrayList fealst = new ArrayList();
            double[] feaVector = SimilarClsWord.getFeaVector(str1, ref fealst);

            ArrayList fealst2 = new ArrayList();
            double[] feaVector2 = SimilarClsWord.getFeaVector(str2, ref fealst2);

            ArrayList allFealst = SimilarCls.unifyFealst(fealst, fealst2);

            return SimilarCls.calSimilarCosineAllFea(allFealst, fealst, fealst2, feaVector, feaVector2) * 100.0;
        }
    }
}
