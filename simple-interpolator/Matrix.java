public class Matrix 
{
    private Value[][] value;
    private int rows;
    private int collums;

    public Matrix(int rows, int collums)
    {
        this.collums = collums;
        this.rows = rows;
        this.value = new Value[rows][collums];
    }

    public void add(int row, int collum, double data)
    {  
        if(this.rows <= row)
            resize(this.rows * 2);
        value[row][collum] =  new Value(data);
    }

    public Matrix getTransposed()
    {
        Matrix tmp = new Matrix(this.collums, this.rows);

        for(int i = 0; i < tmp.collums; i++)
            for(int j = 0; j < tmp.rows; j++)
                tmp.value[j][i] = new Value(this.value[i][j].get_data());
        
        return tmp;
    }

    public Matrix mergeMatrix(Matrix first, Matrix second)
    {   
        Matrix tmp = new Matrix(first.rows, second.collums);

        for(int i = 0; i < first.rows; i++)
        {
            for(int j = 0; j < second.collums; j++)
            {
                double sum = 0;
                
                for(int k = 0; k < second.rows; k++)
                    sum += first.value[i][k].get_data() * second.value[k][j].get_data();
                
                tmp.value[i][j] = tmp.new Value(sum);
            }
        }
        

        return tmp;
    }

    public double get_value(int row, int collum)
    {
        return this.value[row][collum].get_data();
    }

    public Matrix getInverse(Matrix matrix)
    {
        double det = (matrix.value[0][0].get_data() * (matrix.value[1][1].get_data() * matrix.value[2][2].get_data() -  matrix.value[1][2].get_data() *  matrix.value[2][1].get_data())) - 
        (matrix.value[0][1].get_data() * (matrix.value[1][0].get_data() * matrix.value[2][2].get_data() -  matrix.value[1][2].get_data() *  matrix.value[2][0].get_data())) + 
        (matrix.value[0][2].get_data() * (matrix.value[1][0].get_data() * matrix.value[2][1].get_data() -  matrix.value[1][1].get_data() *  matrix.value[2][0].get_data()));

        Matrix tmp = new Matrix(3,3);

        tmp.value[0][0] = tmp.new Value((matrix.value[1][1].get_data() * matrix.value[2][2].get_data() - matrix.value[1][2].get_data() * matrix.value[2][1].get_data())/det);
        tmp.value[0][1] = tmp.new Value(-(matrix.value[1][0].get_data() * matrix.value[2][2].get_data() - matrix.value[1][2].get_data() * matrix.value[2][0].get_data())/det);
        tmp.value[0][2] = tmp.new Value((matrix.value[1][0].get_data() * matrix.value[2][1].get_data() - matrix.value[1][1].get_data() * matrix.value[2][0].get_data())/det);

        tmp.value[1][0] = tmp.new Value(-(matrix.value[0][1].get_data() * matrix.value[2][2].get_data() - matrix.value[0][2].get_data() * matrix.value[2][1].get_data())/det);
        tmp.value[1][1] = tmp.new Value((matrix.value[0][0].get_data() * matrix.value[2][2].get_data() - matrix.value[0][2].get_data() * matrix.value[2][0].get_data())/det);
        tmp.value[1][2] = tmp.new Value(-(matrix.value[0][0].get_data() * matrix.value[2][1].get_data() - matrix.value[0][1].get_data() * matrix.value[2][0].get_data())/det);

        tmp.value[2][0] = tmp.new Value((matrix.value[0][1].get_data() * matrix.value[1][2].get_data() - matrix.value[0][2].get_data() * matrix.value[1][1].get_data())/det);
        tmp.value[2][1] = tmp.new Value(-(matrix.value[0][0].get_data() * matrix.value[1][2].get_data() - matrix.value[0][2].get_data() * matrix.value[1][0].get_data())/det);
        tmp.value[2][2] = tmp.new Value((matrix.value[0][0].get_data() * matrix.value[1][1].get_data() - matrix.value[0][1].get_data() * matrix.value[1][0].get_data())/det);


        return tmp;
    }

    public void resize(int length)
    {
        int n;
        Matrix tmp = new Matrix(length, this.collums);

        if(length < value.length)
            n = length;
        else    
            n = value.length; 

            for(int i = 0; i < n; i++)
                for(int j = 0; j < tmp.collums; j++)
                    tmp.value[i][j] = new Value(this.value[i][j].get_data());

        this.value = tmp.value;
        this.rows = tmp.rows;
        this.collums = tmp.collums;
    }

    public void printAll()
    {
        for(int i = 0; i < value.length; i++)
        {
            for(int j = 0; j < value[i].length; j++)
                System.out.print(value[i][j].get_data() + " ");
            System.out.print("\n");
        }

        System.out.println("rows: " + this.rows + " length: " + this.collums);
    }



    private class Value
    {
        private double data;

        public Value(double data)
        {
            set_data(data);
        }

        public void set_data(double data)
        {
            this.data = data;
        }

        public double get_data()
        {
            return this.data;
        }
    }
}