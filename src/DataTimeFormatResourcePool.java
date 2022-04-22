import java.text.SimpleDateFormat;
import java.util.Date;

class DataTimeFormatResourcePool extends ResourcePool<SimpleDateFormat> {

    DataTimeFormatResourcePool(int size, Boolean dynamicCreation) {
        super(size, dynamicCreation);
        createPool();
    }

    @Override
    protected SimpleDateFormat createObject() {
        return new SimpleDateFormat("yyyyMMdd");
    }

    public Date convert(String input) throws Exception {
        SimpleDateFormat format = acquire();
        try {
            return format.parse(input);
        } finally {
            recycle(format);
        }
    }
}