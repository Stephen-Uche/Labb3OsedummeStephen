package se.iths.labb3osedummestephen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    HelloController controller = new HelloController();
    String test1 = "test1";


    @Test
    void chatBoxInput() {
        controller.model.chatBoxInputProperty().setValue(test1);

        var expected = "test1";
        var actual = controller.model.chatBoxInputProperty().getValue();

        assertEquals(expected, actual);
    }

}
