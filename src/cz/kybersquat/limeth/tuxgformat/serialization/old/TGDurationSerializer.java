package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.herac.tuxguitar.song.models.TGDivisionType;
import org.herac.tuxguitar.song.models.TGDuration;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGDurationSerializer implements JsonSerializer<TGDuration>
{
	@Override
	public JsonElement serialize(TGDuration duration, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonDuration = new JsonObject();
		TGDivisionType division = duration.getDivision();

		jsonDuration.addProperty("value", duration.getValue());
		jsonDuration.addProperty("time", duration.getTime());
		jsonDuration.add("division", context.serialize(division));

		return jsonDuration;
	}
}
