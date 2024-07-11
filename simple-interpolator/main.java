import java.util.Scanner;
import java.io.FileReader;
import java.io.PrintWriter;

public class main {
    public static void main(String args[])
{

    String file_name = args[0];
    Matrix base = new Matrix(10, 3);
    Matrix last = new Matrix(10, 1);

        try(FileReader file = new FileReader(file_name); Scanner scanner = new Scanner(file); PrintWriter print = new PrintWriter("Result.txt"))
        {
            int i = 0;
            while(scanner.hasNextLine())
            {
                String[] line = scanner.nextLine().split(",");
                
                double data = Double.parseDouble(line[0]);

                base.add(i, 0, (data * data));
                base.add(i, 1, data);
                base.add(i, 2, 1);

                data = Double.parseDouble(line[1]);
                last.add(i, 0, data);
                i++;
            }

            base.resize(i);
            last.resize(i);

            Matrix transposed = base.getTransposed();

            Matrix a = base.mergeMatrix(transposed, base);
            a = a.getInverse(a);

            Matrix b = base.mergeMatrix(transposed, last);
            
            Matrix result = base.mergeMatrix(a,b);
            print.println(result.get_value(0, 0) + "*x^2 + (" + result.get_value(1, 0) + ")*x + (" + result.get_value(2, 0) + ")");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
