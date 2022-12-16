//
//  SearchScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared
import Utils
import DetailsFeature

public struct SearchScreen: View {
    @ObservedObject var nativeVM = NativeViewModel(viewModel: SearchViewModel.init())

    @State var searchText: String = ""

    public init() {}

    public var body: some View {
        ZStack {
//            VStack {
//                SearchBar(text: $searchText)
//                    .alignmentGuide(.top) { $0[.top] }
//                    .onChange(of: self.searchText) { text in
//                        nativeVM.viewModel.onSearchTextChanged(text: text)
//                    }
//
//                ZStack(alignment: .top) {
//                    switch nativeVM.state {
//                    case is SearchViewModelStateResults:
//                        guard let results = nativeVM.state as? SearchViewModelStateResults else {
//                            EmptyView()
//                        }
//                        ScrollView(.vertical) {
//                            LazyVStack(spacing: 14) {
//                                ForEach(results, id: \.id) { result in
//                                    NavigationLink {
//                                        DetailsScreen(id: result.id, word: result.value)
//                                    } label: {
//                                        SearchResultRow(model: result)
//                                    }
//                                    .buttonStyle(.plain)
//                                }
//                            }
//                            .padding(.vertical)
//                        }
//                    case is SearchViewModelStateEmptyResult: EmptyView()
//                    default: EmptyView()
//                    }
//                }
//                Spacer()
//            }
        }
    }
}
