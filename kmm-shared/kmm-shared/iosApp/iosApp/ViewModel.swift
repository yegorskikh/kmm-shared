//
//  ViewModel.swift
//  iosApp
//
//  Created by Yegor Gorskikh on 13.06.2025.
//

import Foundation
import shared

@MainActor
class EpisodesViewModel: ObservableObject {
    @Published var episodes: [Episode] = []
    @Published var isLoading = false
    @Published var sortAscending = true

    
    func fetchEpisodes() async {
        isLoading = true
        do {
            let response: HttpResponse<EpisodesResponse> = try await ApiFactory().getApi().episodeGet()
            let episodesResponse = try await response.body().results
            self.episodes = episodesResponse ?? []
        } catch {
            print("Ошибка загрузки эпизодов:", error)
        }
        isLoading = false
    }

    func sort() {
        sortAscending.toggle()
//        episodes.sort { sortAscending ? $0.name < $1.name : $0.name > $1.name }
    }
}

