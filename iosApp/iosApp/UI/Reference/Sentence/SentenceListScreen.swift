//
//  SentenceListScreen.swift
//  iosApp
//
//  Created by Suraj GP on 07/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SentenceListScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var disptacher = Dispatcher(viewModel: SentenceListViewModel.init())
    
    let word: String
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                AppTopBar(navigateUpIcon: "arrow.backward", title: "Sentences for \(self.word)") {
                    self.presentationMode.wrappedValue.dismiss()
                }
                .padding()
                
                if let state = self.disptacher.state {
                    ScrollView(.vertical) {
                        LazyVStack {
                            ForEach(state.sentences, id: \.self) { sentence in
                                NavigationLink {
                                    SentenceDetailsScreen(id: sentence.id)
                                } label: {
                                    SentenceRow(sentence: sentence)
                                        .padding(.horizontal)
                                        .padding(.bottom, 8)
                                }
                            }
                        }
                        .padding(.vertical)
                    }
                }
                
                Spacer()
            }
        }
        .onAppear {
            self.disptacher.with(intent: SentenceListViewModelIntentInitWith(word: self.word))
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
    
}
