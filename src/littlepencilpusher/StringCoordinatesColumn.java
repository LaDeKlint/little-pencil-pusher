package littlepencilpusher;

public class StringCoordinatesColumn {


// constructer

    public static void printCoordinatesByColumn(int m[][])
    {
        StringBuilder resultByColumn = new StringBuilder();
        
        int currentValue;
        
        int startIndex;
        
        startIndex = 0;
        
        for (int i = 1 ; i < m.length ; i++)
        {
            
            currentValue = m[i][0];
            
            
            
            for ( int j = 1 ; j < m[i].length; j++)
            {
                if (currentValue != m[i][j])
                {
                    updateResult(resultByColumn, currentValue, i, startIndex, j-1);
                    
                    currentValue = m[i][j];
                    
                    startIndex = i;
                }
            }
            updateResult(resultByColumn, currentValue, i, startIndex, m[i].length - 1);
            
        }System.out.println(resultByColumn.toString());
    }
    
    

    public static void updateResult(StringBuilder resultByColumn, int value, int row, int startIndex, int endIndex)
    {
        int steps = 100;
        // her vil jeg gerne lave min x movement og min y movement.
    int track;    
    
        if (startIndex == endIndex)
        {
            // hvis der kun er en, så skal du skrive startindex og 1 * steps
            track = 1 * steps;
            
            addCoordinates(resultByColumn, row, startIndex, track);
            
        }
        else
        {
        
            track = (endIndex - startIndex) * steps;
            
            addCoordinates(resultByColumn, row, startIndex, track);
        }
    
        switch (value)
        {
            case 255:
            resultByColumn.append("z_up");
            break;
            
            case 0:
            resultByColumn.append("draw");
            break;
        }
        resultByColumn.append(" ; ");
    
    }

    public static void addCoordinates(StringBuilder resultByRow, int row, int column, int track)
    {
        resultByRow.append(column);
        resultByRow.append(", ");
        resultByRow.append(row);
        resultByRow.append(", ");
        resultByRow.append(track);
    }
    
}
