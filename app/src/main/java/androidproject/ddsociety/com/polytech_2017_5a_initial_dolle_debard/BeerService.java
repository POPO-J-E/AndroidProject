package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;


import java.util.List;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.dummy.Beer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerService{
    public static final String ENDPOINT = "https://api.punkapi.com/v2/";

    @GET("beers")
    Call<List<Beer>> listBeers();

}
