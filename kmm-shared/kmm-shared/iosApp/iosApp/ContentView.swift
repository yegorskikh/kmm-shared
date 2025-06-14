
import shared
import SwiftUI

struct EpisodesListView: View {
    @StateObject private var viewModel = EpisodesViewModel()

    var body: some View {
        NavigationView {
            VStack {
                HStack {
                    Text("Episodes")
                        .font(.largeTitle)
                        .bold()
                    Spacer()
                    Button(action: {
                        viewModel.sort()
                    }) {
                        Image(systemName: viewModel.sortAscending ? "arrow.down" : "arrow.up")
                        Text(viewModel.sortAscending ? "A → Z" : "Z → A")
                    }
                    .padding(.trailing)
                }
                .padding(.top)

                if viewModel.isLoading {
                    ProgressView("Loading...")
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                } else {
                    List(viewModel.episodes, id: \.id) { episode in
                        VStack(alignment: .leading) {
                            Text(episode.name ?? "")
                                .font(.headline)
                            Text("Episode: \(episode.episode ?? "-")")
                                .font(.subheadline)
                                .foregroundColor(.secondary)
                        }
                    }
                }
            }
            .navigationBarHidden(true)
        }
        .task {
            await viewModel.fetchEpisodes()
        }
    }
}
