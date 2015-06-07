package cz.kybersquat.limeth.tuxgformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.kybersquat.limeth.tuxgformat.serialization.ParentExclusionStrategy;
import org.herac.tuxguitar.gui.editors.tab.TGFactoryImpl;
import org.herac.tuxguitar.io.base.TGFileFormatException;
import org.herac.tuxguitar.io.tg.TGInputStream;
import org.herac.tuxguitar.song.models.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws TGFileFormatException, FileNotFoundException
    {
	    System.out.println(Arrays.stream(args).reduce(null, (a, b) -> a == null ? b : a + " " + b));

	    InputStream rawInput;

	    if(args.length <= 0)
		    rawInput = System.in;
	    else
	        rawInput = new FileInputStream(new File(args[0]));

	    toJson(rawInput, System.out);
    }

	public static void toJson(InputStream input, Appendable appendableOutput) throws TGFileFormatException
	{
		TGInputStream tgInput = new TGInputStream();

		tgInput.init(new TGFactoryImpl(), input);

		ParentExclusionStrategy exclusionStrategy = new ParentExclusionStrategy().exclude(TGSong.class)
		                                                                         .exclude(TGTrack.class, TGSong.class)
		                                                                         .exclude(TGMeasure.class, TGTrack.class)
		                                                                         .exclude(TGBeat.class, TGMeasure.class)
		                                                                         .exclude(TGVoice.class, TGBeat.class);
		TGSong song = tgInput.readSong();
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(exclusionStrategy)
		                             .setPrettyPrinting()
									 .serializeNulls()
		                             .create();

		gson.toJson(song, appendableOutput);
	}
}
