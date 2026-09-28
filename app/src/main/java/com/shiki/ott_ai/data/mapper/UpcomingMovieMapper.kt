package com.shiki.ott_ai.data.mapper

import com.shiki.ott_ai.domain.model.ResultUpcoming
import com.shiki.ott_ai.data.remote.dto.ResultUpcomingDto

fun ResultUpcomingDto.toDomain(): ResultUpcoming  {
   return ResultUpcoming(
       adult = adult ?: false,
       backdroppath = backdrop_path ?: "",
       genreids = genre_ids ?: emptyList(),
       id = id ?: 0,
       originallanguage = original_language ?: "",
       originaltitle = original_title ?: "",
       overview = overview ?: "",
       popularity = popularity ?: 0.0,
       posterPath =  poster_path ?: "",
       releasedate = release_date ?: "",
       title = title ?: "",
       video = video ?: false,
       voteaverage = vote_average ?: 0.0,
       votecount = vote_count ?: 0
   )
}