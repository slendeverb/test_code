using System;
using System.IO;
using System.Linq;

namespace testC_
{
    internal class Program
    {
        static void Main(string[] args)
        {
            FileStream inStream = null, outStream = null;
            StreamReader inReader = null;
            StreamWriter outWriter = null;
            TextReader oldIn = Console.In;
            TextWriter oldOut = Console.Out;

            try
            {
                inStream = new FileStream("../../in.txt", FileMode.Open, FileAccess.Read);
                inReader = new StreamReader(inStream);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            Console.SetIn(inReader);

            try
            {
                outStream = new FileStream("../../out.txt", FileMode.OpenOrCreate, FileAccess.Write);
                outWriter = new StreamWriter(outStream);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            Console.SetOut(outWriter);

            int T = Convert.ToInt32(Console.ReadLine());
            while (T-- != 0)
            {
                solve();
            }
            inReader.Close(); outWriter.Close();
            inStream.Close(); outStream.Close();
            Console.SetIn(oldIn); Console.SetOut(oldOut);

        }

        static void solve()
        {
            int n = Convert.ToInt32(Console.ReadLine());
            string input = Console.ReadLine();
            var arr1 = input.Split(new char[] { ' ', ',' }).Where(items => !string.IsNullOrEmpty(items)).ToList();
            foreach (string s in arr1)
            {
                Console.Write(s + " ");
            }
            Console.WriteLine();

            int m = Convert.ToInt32(Console.ReadLine());
            input = Console.ReadLine();
            var arr2 = input.Split(new char[] { ' ', ',' }).Where(items => !string.IsNullOrEmpty(items)).ToList();
            foreach (string s in arr2)
            {
                Console.Write(s + " ");
            }
            Console.WriteLine();

        }

    }

}
