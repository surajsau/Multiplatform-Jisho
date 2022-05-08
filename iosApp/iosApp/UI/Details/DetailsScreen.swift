//
//  DetailsScreen.swift
//  iosApp
//
//  Created by Suraj GP on 03/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct DetailsScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    let id: Int64
    let word: String
    
    @ObservedObject var dispatcher = Dispatcher(viewModel: DetailsViewModel.init())
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                AppTopBar(navigateUpIcon: "arrow.backward", title: ""){
                    self.presentationMode.wrappedValue.dismiss()
                }
                .padding()
                
                if let state = self.dispatcher.state {
                    
                    ScrollView(showsIndicators: false) {
                        VStack(spacing: 16) {
                            
                            DetailsHeader(model: state.header)
                            
                            if state.showAlternative {
                                DetailsAlternatives(alternative: state.alternatives)
                            }
                            
                            if state.showKanji {
                                DetailsKanji(items: state.kanjis)
                            }
                            
                            if state.showMeanings {
                                DetailsMeanings(meanings: state.meanings)
                            }
                            
                            if state.showSentences {
                                DetailsSentence(model: state.sentences, word: self.word)
                            }
                            
                            if state.showConjugation {
                                DetailsConjugation(model: state.conjugations!)
                            }
                        }
                        .padding()
                    }
                }
                
                Spacer()
            }
        }
        .onAppear {
            dispatcher.with(intent: DetailsViewModelIntentInitWith(id: self.id, word: self.word))
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
