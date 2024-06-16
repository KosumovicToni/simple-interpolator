import java.util.Scanner;
import java.io.FileReader;

public class main {
    public static void main(String args[])
{

    String file_name = args[0];
    Matrix base = new Matrix(10, 3);
    Matrix last = new Matrix(10, 1);

        try(FileReader file = new FileReader(file_name); Scanner scanner = new Scanner(file))
        {
            int i = 0;
            while(scanner.hasNextLine())
            {
                String[] line = scanner.nextLine().split(";");
                
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
            a.printAll();
            a = a.getInverse(a);

            Matrix b = base.mergeMatrix(transposed, last);
            b.printAll();
            
            Matrix result = base.mergeMatrix(a,b);
            result.printAll();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
