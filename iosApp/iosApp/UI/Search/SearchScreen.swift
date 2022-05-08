//
//  SearchScreen.swift
//  iosApp
//
//  Created by Suraj GP on 25/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

struct SearchScreen: View {
    
    @ObservedObject var dispatcher = Dispatcher(viewModel: SearchViewModel.init())
    
    @State private var searchText: String = ""
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)

            VStack {
                SearchBar(text: $searchText)
                    .alignmentGuide(.top) { $0[.top] }
                    .onChange(of: self.searchText) { text in
                        self.dispatcher.with(intent: SearchViewModelIntentSearchTextChanged(text: text))
                    }

                ZStack(alignment: .top) {
                    switch self.dispatcher.state {
                    case is SearchViewModelStateResults:
                        let results = (self.dispatcher.state as! SearchViewModelStateResults).value
                        ScrollView(.vertical) {
                            LazyVStack(spacing: 14) {
                                ForEach(results, id: \.id) { result in
                                    NavigationLink {
                                        DetailsScreen(id: result.id, word: result.value)
                                    } label: {
                                        SearchResultRow(model: result)
                                    }
                                    .buttonStyle(.plain)
                                }
                            }
                            .padding(.vertical)
                        }
                     
                    case is SearchViewModelStateEmptyResult: EmptyView()
                     
                    default: EmptyView()
                    }
                }
                
                Spacer()
            }
        }
    }
}
