package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGSong;
import org.herac.tuxguitar.song.models.TGTrack;

import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * @author Limeth
 */
@Deprecated
public class TGSongSerializer implements JsonSerializer<TGSong>
{
	@Override
	public JsonElement serialize(TGSong song, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject root = new JsonObject();
		Iterator<TGTrack> tracks = song.getTracks();
		JsonArray jsonTracks = new JsonArray();

		while(tracks.hasNext())
		{
			TGTrack track = tracks.next();
			JsonElement jsonTrack = context.serialize(track);
			jsonTracks.add(jsonTrack);
		}

		root.addProperty("album", song.getAlbum());
		root.addProperty("artist", song.getArtist());
		root.addProperty("author", song.getAuthor());
		root.addProperty("comments", song.getComments());
		root.addProperty("copyright", song.getCopyright());
		root.addProperty("date", song.getDate());
		root.addProperty("name", song.getName());
		root.addProperty("transcriber", song.getTranscriber());
		root.addProperty("writer", song.getWriter());
		root.add("tracks", jsonTracks);

		return root;
	}
}
