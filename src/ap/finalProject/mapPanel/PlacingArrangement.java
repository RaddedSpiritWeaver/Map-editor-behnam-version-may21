package ap.finalProject.mapPanel;

import ap.finalProject.mapPanel.terrains.TerrainEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Created by sarb on 5/20/17.
 */
public class PlacingArrangement implements KeyListener
{
    private static long lastId;

    private Vector<TerrainEvent> events;
    private Vector<TerrainEvent> returnedEvents;

    private MapPanel mapPanel;
    private boolean isNewTile;

    public PlacingArrangement(MapPanel mapPanel)
    {
        this.mapPanel = mapPanel;
        events = new Vector<>();
        returnedEvents = new Vector<>();
    }

    public void addEvent(TerrainEvent terrainEvent)
    {
        events.add(terrainEvent);
        returnedEvents.removeAllElements();
    }

    public void undo()
    {
        if (events.size() == 0)
            return;

        long undoId = events.lastElement().getId();

        while (events.size() !=0 && events.lastElement().getId() == undoId)
        {
            TerrainEvent event = events.remove(events.size()-1);
            returnedEvents.add(event);

            mapPanel.setTile(event.getOldTile(),event.getX(),event.getY());
        }
    }

    public void redo()
    {
        if (returnedEvents.size() == 0)
            return;
        long redoId = returnedEvents.lastElement().getId();

        while (returnedEvents.size() != 0 && returnedEvents.lastElement().getId() == redoId)
        {
            TerrainEvent event = returnedEvents.remove(returnedEvents.size()-1);
            events.add(event);

            mapPanel.setTile(event.getNewTile(),event.getX(),event.getY());
        }
    }

    public void nextId()
    {
        lastId++;
    }

    public static long getLastId()
    {
        return lastId;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        switch (e.getKeyChar())
        {
            case 'u':
                undo();
                break;
            case 'r':
                redo();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
