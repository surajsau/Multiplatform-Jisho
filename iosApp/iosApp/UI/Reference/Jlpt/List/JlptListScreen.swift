//
//  JlptListScreen.swift
//  iosApp
//
//  Created by Suraj GP on 10/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct JlptListScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var dispatcher = Dispatcher(viewModel: JlptListViewModel.init())
    
    let level: Int
    
    var body: some View {
        ZStack {
            
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {

                AppTopBar(navigateUpIcon: "arrow.backward", title: self.dispatcher.state?.title ?? "") {
                    self.presentationMode.wrappedValue.dismiss()
                }
                .padding()
                
                if let state = self.dispatcher.state {
                    
                    if state.isLoading {
                        ProgressView()
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                    } else {
                        ScrollView(.vertical) {
                            LazyVStack(spacing: 14) {
                                ForEach(state.items, id: \.self) { result in
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
                        
                        Spacer()
                    }
                }
            }
        }
        .onAppear {
            self.dispatcher.with(intent: JlptListViewModelIntentInitWith(level: Int32(self.level)))
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
