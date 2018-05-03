package Speech2Text;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConvertSpeech {
	private SpeechClient speech;
	private RecognitionConfig config;
	private RecognitionAudio audio;
	private RecognizeResponse response;
	private SpeechRecognitionAlternative alternative;
	private List<SpeechRecognitionResult> results;
	
	public ConvertSpeech() throws IOException {
		this.setSpeech(SpeechClient.create());
	}
	
	public String convert(String fileName) throws Exception {
		String resultado = "";
		Path path = Paths.get(fileName);
		   
	    byte[] data = Files.readAllBytes(path);
	    ByteString audioBytes = ByteString.copyFrom(data);

	    // Builds the sync recognize request
	    	this.setConfig(RecognitionConfig.newBuilder()
		        .setEncoding(AudioEncoding.LINEAR16)
		        .setSampleRateHertz(8000)
		        .setLanguageCode("es_ES")
		        .build());
	   
	    this.setAudio(RecognitionAudio.newBuilder()
		        .setContent(audioBytes)
		        .build());

	    // Performs speech recognition on the audio file
	    this.setResponse(speech.recognize(config, audio));
	    this.setResults(response.getResultsList());

	    for (SpeechRecognitionResult result: this.getResults()) {
	    		// There can be several alternative transcripts for a given chunk of speech. Just use the
	    		// first (most likely) one here.
	    		this.setAlternative(result.getAlternativesList().get(0));	    
	    		resultado += alternative.getTranscript();
	    }
	    
	    return resultado;
	}
	
	public void cerrar() throws Exception {
		 this.getSpeech().close();
	}

	public SpeechClient getSpeech() {
		return speech;
	}

	public void setSpeech(SpeechClient speech) {
		this.speech = speech;
	}

	public RecognitionConfig getConfig() {
		return config;
	}

	public void setConfig(RecognitionConfig config) {
		this.config = config;
	}

	public RecognitionAudio getAudio() {
		return audio;
	}

	public void setAudio(RecognitionAudio audio) {
		this.audio = audio;
	}

	public RecognizeResponse getResponse() {
		return response;
	}

	public void setResponse(RecognizeResponse response) {
		this.response = response;
	}

	public SpeechRecognitionAlternative getAlternative() {
		return alternative;
	}

	public void setAlternative(SpeechRecognitionAlternative alternative) {
		this.alternative = alternative;
	}

	public List<SpeechRecognitionResult> getResults() {
		return results;
	}

	public void setResults(List<SpeechRecognitionResult> results) {
		this.results = results;
	}	
}
