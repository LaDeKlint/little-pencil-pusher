package littlepencilpusher;

public class StringCoordinatesRow
{
    


// constructer

    public static void printCoordinatesByRow(int m[][])
    {
        StringBuilder resultByRow = new StringBuilder();
        
        int currentValue;
        
        int startIndex;
        
        for (int i = 0 ; i < m.length ; i++)
        {
            
            currentValue = m[i][0];
            
            startIndex = 0;
            
            for ( int j = 1 ; j < m[i].length; j++)
            {
                if (currentValue != m[i][j])
                {
                    updateResult(resultByRow, currentValue, i, startIndex, j-1);
                    
                    currentValue = m[i][j];
                    
                    startIndex = j;
                }
            }
            updateResult(resultByRow, currentValue, i, startIndex, m[i].length - 1);
            
        }System.out.println(resultByRow.toString());
    }
    
 

    public static void updateResult(StringBuilder resultByRow, int value, int row, int startIndex, int endIndex)
    {
        int steps = 100;
        // her vil jeg gerne lave min x movement og min y movement.
    int track;    
    
        if (startIndex == endIndex)
        {
            // hvis der kun er en, så skal du skrive startindex og 1 * steps
            track = 1 * steps;
            
            addCoordinates(resultByRow, row, startIndex, track);
            
        }
        else
        {
        
            track = (endIndex - startIndex) * steps;
            
            addCoordinates(resultByRow, row, startIndex, track);
        }
    
        switch (value)
        {
            case 255:
            resultByRow.append("z_up");
            break;
            
            case 0:
            resultByRow.append("draw");
            break;
        }
        resultByRow.append(" ; ");
    
    }

    public static void addCoordinates(StringBuilder resultByRow, int row, int column, int track)
    {
        resultByRow.append(row);
        resultByRow.append(", ");
        resultByRow.append(column);
        resultByRow.append(", ");
        resultByRow.append(track);
    }
}
