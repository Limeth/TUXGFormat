package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.herac.tuxguitar.song.models.TGDivisionType;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGDivisionTypeSerializer implements JsonSerializer<TGDivisionType>
{
	@Override
	public JsonElement serialize(TGDivisionType division, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonDivision = new JsonObject();

		jsonDivision.addProperty("enters", division.getEnters());
		jsonDivision.addProperty("timers", division.getTimes());

		return jsonDivision;
	}
}
