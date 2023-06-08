package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.util.BaseController;

public class UebersichtTabController extends BaseController  implements IGUIEventListener {

    private UebersichtTabComponent component;

    public UebersichtTabController(UebersichtTabComponent component) {
        this.component = component;
        addObserver(component);
    }

    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        System.out.println("GUI:" + guiEvent.getData());
        fireUpdateEvent(new UpdateEvent(this,Commands.SOME_VALUE_CHANGED, guiEvent.getData()));
    }

    public enum Commands implements EventCommand {

        SOME_VALUE_CHANGED("UebersichtTabController.valueChanged", String.class);

        String cmdText;
        Class<?> payloadType;

        Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return cmdText;
        }

        @Override
        public Class<?> getPayloadType() {
            return payloadType;
        }
    }
}
